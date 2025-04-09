import { Component } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';

@Component({
  selector: 'app-crear-cliente',
  templateUrl: './crear-cliente.component.html',
  styleUrls: ['./crear-cliente.component.css']
})
export class CrearClienteComponent {
  nuevoCliente: Cliente = {
    id: -1,
    nombre: "",
    cedula: "",
    celular: "",
    correo: ""
  }
}
