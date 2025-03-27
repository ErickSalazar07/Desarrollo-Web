import { Component, Input } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-formulario-mascota',
  templateUrl: './formulario-mascota.component.html',
  styleUrls: ['./formulario-mascota.component.css']
})
export class FormularioMascotaComponent {
  @Input()
  mascota!: Mascota

  constructor(private servicioMascota: MascotaService) {

  }

  guardarMascota(event: Event) {
    event.preventDefault();
    this.servicioMascota.guardarMascota(this.mascota);
  }
}
