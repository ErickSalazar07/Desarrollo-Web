import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-crear-mascota',
  templateUrl: './crear-mascota.component.html',
  styleUrls: ['./crear-mascota.component.css']
})
export class CrearMascotaComponent {

  nuevaMascota: Mascota = {
    id: -1,
    nombre: "",
    edad: 0,
    estadoActivo: true,
    foto: "",
    peso: 0.0,
    cedulaCliente: "",
    raza: "Perro"
  }

}
