import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';

@Component({
  selector: 'app-tratamientos-veterinario',
  templateUrl: './tratamientos-veterinario.component.html',
  styleUrls: ['./tratamientos-veterinario.component.css']
})
export class TratamientosVeterinarioComponent {

  tratamientos: Tratamiento[] = [];
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombreTratamiento';

  constructor(private http: HttpClient, private service: TratamientoService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const cedulaParam = this.route.snapshot.paramMap.get('cedula');
    if (cedulaParam !== null) {
      this.service.obtenerTratamientosVeterinario(cedulaParam).subscribe(tratamientos => {
        this.tratamientos = tratamientos;

      });
    } else {
      console.error('ID no encontrado en la ruta');
    }
  }

  get tratamientosFiltrados() {
    if (!this.terminoBusqueda) return this.tratamientos;

    const valor = this.terminoBusqueda.toLowerCase();

    return this.tratamientos.filter(tratamiento => {
      switch (this.filtroSeleccionado) {
        case 'nombreTratamiento':
          return tratamiento.nombreTratamiento.toLowerCase().includes(valor);
        case 'drogaAsignada':
          return tratamiento.drogaAsignada?.nombre?.toLowerCase().includes(valor);
        case 'mascota':
          return tratamiento.mascota?.nombre?.toLowerCase().includes(valor);
        
        default:
          return true;
      }
    });
  }
}
