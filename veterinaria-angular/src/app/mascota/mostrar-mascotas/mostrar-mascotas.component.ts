import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-mostrar-mascotas',
  templateUrl: './mostrar-mascotas.component.html',
  styleUrls: ['./mostrar-mascotas.component.css']
})
export class MostrarMascotasComponent {
  mascotaSeleccionadaMostrar!: Mascota;
  mascotaSeleccionadaModificar?: Mascota | null =  null;
  mascotas!: Mascota[];
  showFormulario = false;
  constructor(private mascotaServicio: MascotaService) { }

  ngOnInit() {
    this.mascotas = this.mascotaServicio.findAll();
  }

  crearMascota() {
    this.showFormulario = true; 
  }
  formularioCerrado() {
    this.mascotaSeleccionadaModificar = null; 
    this.showFormulario = false;  
  }


  eliminarMascota(mascota: Mascota) {
    this.mascotaServicio.deleteById(mascota.id);
    this.mascotas = this.mascotaServicio.findAll();
  }

  mostrarMascota(mascota: Mascota) {
    this.mascotaSeleccionadaMostrar = mascota;
  }

  actualizarMascota(mascota: Mascota) {
    this.mascotaSeleccionadaModificar = mascota;
    this.showFormulario = true;  
  }

  toggleFormulario() {
    this.showFormulario = !this.showFormulario;
  }
}
