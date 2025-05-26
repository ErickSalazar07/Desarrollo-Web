import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Veterinario } from 'src/app/modelo/veterinario';
import { Router } from '@angular/router';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';
import { AdminService } from 'src/app/servicio/admin.service';

@Component({
  selector: 'app-mostrar-veterinarios',
  templateUrl: './mostrar-veterinarios.component.html',
  styleUrls: ['./mostrar-veterinarios.component.css']
})
export class MostrarVeterinariosComponent implements OnInit {
  veterinarios: Veterinario[] = [];
  rolActivo: string | null = null;
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombre';

  constructor(
    private http: HttpClient,
    private router: Router,
    private service: VeterinarioService,
    private adminServicio: AdminService
  ) {}

  ngOnInit(): void {
    this.service.findAll().subscribe(veterinarios => {
      this.veterinarios = veterinarios;
      this.rolActivo = localStorage.getItem("rolActivo");
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

    cambiarEstadoVeterinario(veterinario: Veterinario) {
      this.service.cambiarEstadoByCedula(veterinario.cedula).subscribe(() => {
        complete: this.service.findAll().subscribe(veterinarios => {
          this.veterinarios = veterinarios;
        });
      });
    }

  obtenerExcelVeterinarios() {
    this.adminServicio.obtenerExcelTablaVeterinario().subscribe(diccionarioStatus => {
      if(diccionarioStatus["status"] !== "ok") alert("Hubo un error enviando el excel, lo sentimos.");
      else alert("Verifique su correo, el excel ya fue enviado.")
    });
  }
}
