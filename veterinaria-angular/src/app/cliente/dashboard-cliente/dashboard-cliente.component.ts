import { Component } from '@angular/core';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { Cliente } from 'src/app/modelo/cliente';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-cliente',
  templateUrl: './dashboard-cliente.component.html',
  styleUrls: ['./dashboard-cliente.component.css']
})
export class DashboardClienteComponent {
  cliente!: Cliente;

  constructor(private servicioCliente: ClienteService, private router:Router) {}

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

  cerrarSesion() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}
