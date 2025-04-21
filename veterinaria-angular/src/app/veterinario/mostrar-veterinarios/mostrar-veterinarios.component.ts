import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Veterinario } from 'src/app/modelo/veterinario';
import { Router } from '@angular/router';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-mostrar-veterinarios',
  templateUrl: './mostrar-veterinarios.component.html',
  styleUrls: ['./mostrar-veterinarios.component.css']
})
export class MostrarVeterinariosComponent implements OnInit {
  veterinarios: Veterinario[] = [];
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombre';

  constructor(private http: HttpClient, private router: Router, private service: VeterinarioService) {}

  ngOnInit(): void {
    this.service.findAll().subscribe(veterinarios => {
      this.veterinarios = veterinarios;
    });
  }


  get veterinariosFiltrados() {
    if (!this.terminoBusqueda) return this.veterinarios;

    const valor = this.terminoBusqueda.toLowerCase();

    return this.veterinarios.filter(v => {
      switch (this.filtroSeleccionado) {
        case 'nombre':
          return v.nombre.toLowerCase().includes(valor);
        case 'especialidad':
          return v.especialidad.toLowerCase().includes(valor);
        case 'cedula':
          return v.cedula.includes(valor);
        default:
          return true;
      }
    });
  }
}
