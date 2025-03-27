import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';

@Component({
  selector: 'app-actualizar-mascota',
  templateUrl: './actualizar-mascota.component.html',
  styleUrls: ['./actualizar-mascota.component.css']
})
export class ActualizarMascotaComponent {
  actualizarMascota!: Mascota;


}
