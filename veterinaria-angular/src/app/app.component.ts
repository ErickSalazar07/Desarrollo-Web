import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MostrarClientesComponent } from './cliente/mostrar-clientes/mostrar-clientes.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,MostrarClientesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'veterinaria-angular';
}
