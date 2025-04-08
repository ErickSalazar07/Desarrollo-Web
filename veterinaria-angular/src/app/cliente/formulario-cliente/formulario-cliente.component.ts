import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent {

  @Input()
  crear!: Boolean;
  @Output()
  eventoAgregarCliente = new EventEmitter<Cliente>();

  constructor(private servicioCliente: ClienteService,private http: HttpClient) {}

  clienteCrear: Cliente = {
    id: -1,
    nombre: "",
    cedula: "",
    celular: "",
    correo: ""
  }

  agregarCliente() {
    console.log(this.clienteCrear)
    this.servicioCliente.addCliente(this.clienteCrear).subscribe();
    this.eventoAgregarCliente.emit(this.clienteCrear);
  }
}
