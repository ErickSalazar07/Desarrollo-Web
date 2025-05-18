import { Component } from '@angular/core';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { Cliente } from 'src/app/modelo/cliente';

@Component({
  selector: 'app-dashboard-cliente',
  templateUrl: './dashboard-cliente.component.html',
  styleUrls: ['./dashboard-cliente.component.css']
})
export class DashboardClienteComponent {
  cliente!: Cliente;

  constructor(private servicioCliente: ClienteService) {}

  ngOnInit(): void {
    this.servicioCliente.clienteActual().subscribe({
      next: (cliente) => {
        this.cliente = cliente;
      },
      error: (err) => {
        console.error('Error al obtener el cliente:', err);
      }
    });
  }
}
