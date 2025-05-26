import { Component, ViewChild, ElementRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  @ViewChild('chatBody') chatBody!: ElementRef;
  chatOpen = false;
  userInput = '';
  messages: { role: 'user' | 'assistant', content: string }[] = [];
  isLoading = false;

  constructor(private http: HttpClient) {}

  toggleChat() {
    this.chatOpen = !this.chatOpen;
    setTimeout(() => this.scrollToBottom(), 100);
  }

  sendMessage() {
  if (!this.userInput.trim() || this.isLoading) return;

  if (!environment.geminiApiKey) {
    this.messages.push({
      role: 'assistant',
      content: 'Error: API Key no configurada. Por favor configura tu API key en el archivo environment.ts'
    });
    return;
  }

  const message = this.userInput;
  this.messages.push({ role: 'user', content: message });
  this.userInput = '';
  this.isLoading = true;
  this.scrollToBottom();

  this.messages.push({ role: 'assistant', content: '...' });
  this.scrollToBottom();

  const headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  const prompt = this.messages
    .filter(m => m.role === 'user')
    .map(m => m.content)
    .join('\n');

  const body = {
    contents: [
      {
        parts: [{ text: prompt }]
      }
    ]
  };

const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${environment.geminiApiKey}`;

  this.http.post<any>(url, body, { headers }).pipe(
    catchError(error => {
      console.error('Error completo:', error);

      let errorMessage = 'Ha ocurrido un error inesperado.';
      if (error.status === 401) {
        errorMessage = '🔑 API Key inválida o expirada. Verifica tu clave.';
      } else if (error.status === 429) {
        errorMessage = '⏰ Has excedido el límite de solicitudes. Intenta de nuevo en unos minutos.';
      } else if (error.status === 500) {
        errorMessage = '🔧 Error del servidor de Gemini. Intenta más tarde.';
      } else if (error.status === 0) {
        errorMessage = '🌐 Error de conexión. Verifica tu internet.';
      } else if (error.error?.error?.message) {
        errorMessage = `❌ ${error.error.error.message}`;
      }

      return of({ error: true, message: errorMessage });
    })
  ).subscribe(res => {
    this.isLoading = false;
    this.messages = this.messages.filter(m => m.content !== '...');

    if (res?.error) {
      this.messages.push({ role: 'assistant', content: res.message });
    } else if (res?.candidates?.[0]?.content?.parts?.[0]?.text) {
      const reply = res.candidates[0].content.parts[0].text.trim();
      this.messages.push({ role: 'assistant', content: reply });
    } else {
      this.messages.push({ role: 'assistant', content: '⚠️ Respuesta inesperada. Intenta de nuevo.' });
    }

    this.scrollToBottom();
  });
}

  scrollToBottom() {
    try {
      setTimeout(() => {
        if (this.chatBody?.nativeElement) {
          this.chatBody.nativeElement.scrollTop = this.chatBody.nativeElement.scrollHeight;
        }
      }, 100);
    } catch (err) {
      console.error('Error al hacer scroll:', err);
    }
  }

  clearChat() {
    this.messages = [];
  }

  onKeyPress(event: KeyboardEvent) {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      this.sendMessage();
    }
  }

  trackByIndex(index: number, item: any): number {
    return index;
  }
}
