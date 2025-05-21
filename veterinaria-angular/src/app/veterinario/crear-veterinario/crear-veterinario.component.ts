import { Component } from '@angular/core';
import { Veterinario } from 'src/app/modelo/veterinario';

@Component({
  selector: 'app-crear-veterinario',
  templateUrl: './crear-veterinario.component.html',
  styleUrls: ['./crear-veterinario.component.css']
})
export class CrearVeterinarioComponent {

  nuevoVeterinario: Veterinario = {
    id: -1,
    nombre: "",
    activo: true,
    foto: "",
    cedula: "",
    especialidad: "",
    contrasena: "",
    user: {
      id: -1,
      username: "",
      password: "",
      roles: []
    }
  }

}
