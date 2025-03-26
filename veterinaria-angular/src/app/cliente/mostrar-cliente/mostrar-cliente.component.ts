import { Component, Input } from '@angular/core';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-mostrar-cliente',
  imports: [],
  templateUrl: './mostrar-cliente.component.html',
  styleUrl: './mostrar-cliente.component.css'
})
export class MostrarClienteComponent {
  @Input()
  cliente!: Cliente;
}
