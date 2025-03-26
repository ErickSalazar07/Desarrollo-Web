import { Component } from '@angular/core';
import { Mascota } from '../mascota';

@Component({
  selector: 'app-mostrar-mascotas',
  imports: [],
  templateUrl: './mostrar-mascotas.component.html',
  styleUrl: './mostrar-mascotas.component.css'
})
export class MostrarMascotasComponent {
  mascotas: Mascota[] = [
    {
      id: 1,
      nombre: "Pachini",
      raza: "Gato",
      edad: 4,
      peso: 4.5,
      enfermedad: "VIF",
      foto: "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-pachini.jpg?raw=true" 
    },
    {
      id: 2,
      nombre: "Zeus",
      raza: "Perro",
      edad: 9,
      peso: 11.7,
      foto: "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-zeus.jpg?raw=true" 
    },
    {
      id: 3,
      nombre: "Figaro",
      raza: "Perro",
      edad: 9,
      peso: 19.5,
      enfermedad: "Cataratas",
      foto: "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/perro-figaro.jpg?raw=true" 
    },
    {
      id: 4,
      nombre: "Lola",
      raza: "Gato",
      edad: 3,
      peso: 6.0,
      foto: "https://github.com/ErickSalazar07/Web/blob/main/imagenes/mascotas/gato-lola.jpg?raw=true" 
    }
  ]
}
