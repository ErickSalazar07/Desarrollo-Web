import { Component } from '@angular/core';
import { Cliente } from '../cliente';
import { CommonModule } from '@angular/common';
import { MostrarClienteComponent } from "../mostrar-cliente/mostrar-cliente.component";

@Component({
  selector: 'app-mostrar-clientes',
  imports: [CommonModule, MostrarClienteComponent],
  templateUrl: './mostrar-clientes.component.html',
  styleUrl: './mostrar-clientes.component.css'
})
export class MostrarClientesComponent {
  clienteMostrar!: Cliente;
  clientes: Cliente[] = [
    {
      id: 1,
      cedula: "18872435",
      nombre: "Juan",
      correo: "juan@email.com",
      celular: "3145194072"
    },
    {
      id: 2,
      cedula: "19812305",
      nombre: "Pedro",
      correo: "pedro@email.com",
      celular: "3045591094"
    },
    {
      id: 3,
      cedula: "27082133",
      nombre: "Luist",
      correo: "luis@email.com",
      celular: "3944193373"
    },
    {
      id: 4,
      cedula: "17190115",
      nombre: "Jualiana",
      correo: "juli@email.com",
      celular: "3305004013"
    }
  ];

  mostrarCliente(cliente: Cliente) {
    this.clienteMostrar = cliente;
  }

}
