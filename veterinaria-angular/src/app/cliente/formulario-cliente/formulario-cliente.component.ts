import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent {

  @Input()
  cliente!: Cliente;
  @Output()
  eventoAgregarCliente = new EventEmitter<Cliente>();

  constructor(private servicioCliente: ClienteService,
    private http: HttpClient,
    private router: Router
  ) {}

  agregarCliente() {
    console.log(this.cliente)
    this.servicioCliente.addCliente(this.cliente).subscribe();
    this.eventoAgregarCliente.emit(this.cliente);
  }

  submitFormulario() {
    if(this.cliente.id === -1) {
      this.servicioCliente.addCliente(this.cliente).subscribe();
    } else {
      this.servicioCliente.updateCliente(this.cliente).subscribe();
    }
    this.router.navigate(['cliente/clientes']);
  }
}
