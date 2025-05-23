import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent {

  mostrarCedula!:boolean;

  @Input()
  cliente!: Cliente;
  @Output()
  eventoAgregarCliente = new EventEmitter<Cliente>();

  constructor(private servicioCliente: ClienteService,
    private location:Location
  ) {}

  ngOnInit(): void {
    this.mostrarCedula = !this.cliente.cedula || this.cliente.cedula !== "";
  }
  submitFormulario() {
    if(this.cliente.id === -1) {
      this.servicioCliente.addCliente(this.cliente).subscribe({
        complete: () => this.location.back()
      });
    } else {
      this.servicioCliente.updateCliente(this.cliente).subscribe({
        complete: () => this.location.back()
      });
    }
  }
}
