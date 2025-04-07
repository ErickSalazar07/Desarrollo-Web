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
  constructor(private http: HttpClient, private router: Router, private service: ClienteService) {}


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
    this.service.deleteById(cliente.id).subscribe(() => {
    this.service.findAll().subscribe(clientes => {
        this.clientes = clientes;
      })
    });
  }
  
}  
