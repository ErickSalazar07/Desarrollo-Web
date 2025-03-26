import { Injectable } from '@angular/core';
import { Cliente } from '../modelo/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor() { }

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
      nombre: "Luis",
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

  findAll(): Cliente[] {
    return this.clientes;
  }

  findById(id: number) {
    const cliente = this.clientes.find(c => c.id === id);
    if(cliente == undefined) return null;
    return cliente;
  }

  deleteById(id: number) {
    const cliente = this.clientes.find(c => c.id === id);
    if(cliente == undefined) return;
    this.clientes = this.clientes.filter(c => c.id !== id);
  }
}
