import { Component, EventEmitter, Output } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent {

  @Output()
  eventoAgregarCliente = new EventEmitter<Cliente>();

  clienteCrear: Cliente = {
    id: -1,
    nombre: "",
    cedula: "",
    celular: "",
    correo: ""
  }

  agregarCliente() {
    console.log(this.clienteCrear)

    this.eventoAgregarCliente.emit(this.clienteCrear);
  }
}
