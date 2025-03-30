import { Component } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-mostrar-clientes',
  templateUrl: './mostrar-clientes.component.html',
  styleUrls: ['./mostrar-clientes.component.css']
})
export class MostrarClientesComponent {
  clienteMostrar!: Cliente;
  clientes!: Cliente[];

  constructor(private clienteServicio: ClienteService) {
  
  }

  ngOnInit(): void {
    this.clientes = this.clienteServicio.findAll();
  }

  mostrarCliente(cliente: Cliente) {
    this.clienteMostrar = cliente;
    console.log("Cliente a mostrar");
    console.log(cliente);
  }

  eliminarCliente(cliente: Cliente) {
    console.warn("Entro a eliminar cliente")
    this.clienteServicio.deleteById(cliente.id);
    this.clientes = this.clienteServicio.findAll();
  }
}
