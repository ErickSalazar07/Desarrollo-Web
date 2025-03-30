import { Component, Input } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';

@Component({
  selector: 'app-mostrar-cliente',
  templateUrl: './mostrar-cliente.component.html',
  styleUrls: ['./mostrar-cliente.component.css']
})
export class MostrarClienteComponent {
  @Input()
  cliente!: Cliente;
}
