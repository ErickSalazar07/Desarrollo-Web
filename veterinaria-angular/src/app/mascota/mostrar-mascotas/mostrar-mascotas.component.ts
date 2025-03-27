import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-mostrar-mascotas',
  templateUrl: './mostrar-mascotas.component.html',
  styleUrls: ['./mostrar-mascotas.component.css']
})
export class MostrarMascotasComponent {
  mascotaMostrar!: Mascota;
  mascotas!: Mascota[];
  showFormulario = false;

  constructor(private mascotaServicio: MascotaService) { }

  ngOnInit() {
    this.mascotas = this.mascotaServicio.findAll();
  }

  eliminarMascota(mascota: Mascota) {
    this.mascotaServicio.deleteById(mascota.id);
    this.mascotas = this.mascotaServicio.findAll();
  }

  mostrarMascota(mascota: Mascota) {
    this.mascotaMostrar = mascota;
  }

  toggleFormulario() {
    this.showFormulario = !this.showFormulario;
  }
}
