import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Veterinario } from 'src/app/modelo/veterinario';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  veterinario!: Veterinario;
  constructor(private http: HttpClient, private route: ActivatedRoute,
      private servicioVeterinario: VeterinarioService) { }
  ngOnInit(): void {
    this.servicioVeterinario.veterinarioActual().subscribe({
      next: (veterinario) => {
        this.veterinario = veterinario;
      },
      error: (err) => {
        console.error('Error al obtener el veterinario:', err);
      }
    });
  }
}
