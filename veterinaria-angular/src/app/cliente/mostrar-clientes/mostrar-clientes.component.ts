import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ClienteService } from 'src/app/servicio/cliente.service';


@Component({
  selector: 'app-mostrar-clientes',
  templateUrl: './mostrar-clientes.component.html',
  styleUrls: ['./mostrar-clientes.component.css']
})
export class MostrarClientesComponent implements OnInit {
  clientes: Cliente[] = [];
  terminoBusqueda: string = '';
  filtroSeleccionado: string = 'nombre';
  
  constructor(
    private http: HttpClient,
    private router: Router,
    private service: ClienteService
  ) {}


  ngOnInit(): void {
    this.service.findAll().subscribe(clientes => {
      this.clientes = clientes;
    });
  }
  

  mostrarCliente(cliente: Cliente) {
    this.router.navigate(['/cliente/mostrar-cliente', cliente.id]);
  }

  eliminarCliente(cliente: Cliente) {
    console.warn("Entro a eliminar cliente");
    this.service.deleteById(cliente.id).subscribe({
      complete: () => this.service.findAll().subscribe(clientes => this.clientes = clientes)
    });
  }
  get clientesFiltrados() {
    if (!this.terminoBusqueda) return this.clientes;
  
    return this.clientes.filter(cliente => {
      const valor = this.terminoBusqueda.toLowerCase();
  
      switch (this.filtroSeleccionado) {
        case 'nombre':
          return cliente.nombre.toLowerCase().includes(valor);
        case 'cedula':
          return cliente.cedula.toString().includes(valor);
          case 'celular':
            return cliente.celular.toString().includes(valor);
        default:
          return true;
      }
    });
  }
  
}  
