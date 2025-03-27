import { Component, Input } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';

@Component({
  selector: 'app-mostrar-mascota',
  templateUrl: './mostrar-mascota.component.html',
  styleUrls: ['./mostrar-mascota.component.css']
})
export class MostrarMascotaComponent {
  @Input()
  mascota!: Mascota

  volver() {
    window.history.back(); // Función para volver a la página anterior
  }
  constructor() { }
}


