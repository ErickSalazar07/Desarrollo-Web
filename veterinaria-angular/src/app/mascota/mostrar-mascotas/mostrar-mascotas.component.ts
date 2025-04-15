import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';
import {HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mostrar-mascotas',
  templateUrl: './mostrar-mascotas.component.html',
  styleUrls: ['./mostrar-mascotas.component.css']
})
export class MostrarMascotasComponent {

  mascotaSeleccionadaMostrar!: Mascota;
  mascotaSeleccionadaModificar?: Mascota | null =  null;
  mascotas: Mascota[]  = [];
  showFormulario = false;
  
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombre';

  constructor(private http: HttpClient, private service: MascotaService, private router: Router) { }

  ngOnInit() {
    this.service.findAll().subscribe(mascotas => {
      this.mascotas = mascotas;
    });
  }

  crearMascota() {
    this.showFormulario = true; 
  }
  formularioCerrado() {
    this.mascotaSeleccionadaModificar = null; 
    this.showFormulario = false;  
  }


  cambiarEstadoMascota(mascota: Mascota) {
    mascota.estadoActivo = !mascota.estadoActivo; 
    this.service.updateMascota(mascota).subscribe(() => {
      this.service.findAll().subscribe(mascotas => {
        this.mascotas = mascotas;
      });
    });
  }
  

  mostrarMascota(mascota: Mascota) {
    this.router.navigate(['veterinario/dashboard/mascota/mostrar-mascota', mascota.id]);
  }

  actualizarMascota(mascota: Mascota) {
    this.mascotaSeleccionadaModificar = mascota;
    this.showFormulario = true;  
  }

  toggleFormulario() {
    this.showFormulario = !this.showFormulario;
  }
  get mascotasFiltradas() {
    if (!this.terminoBusqueda) return this.mascotas;
  
    return this.mascotas.filter(mascota => {
      const valor = this.terminoBusqueda.toLowerCase();
  
      switch (this.filtroSeleccionado) {
        case 'nombre':
          return mascota.nombre.toLowerCase().includes(valor);
        case 'edad':
          return mascota.edad.toString().includes(valor);
        case 'enfermedad':
          return (mascota.enfermedad || '').toLowerCase().includes(valor);
        case 'estadoActivo':
          return (mascota.estadoActivo ? 'si' : 'no').includes(valor);
        default:
          return true;
      }
    });
  }
}

