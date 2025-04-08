import { Component, Input } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-actualizar-cliente',
  templateUrl: './actualizar-cliente.component.html',
  styleUrls: ['./actualizar-cliente.component.css']
})
export class ActualizarClienteComponent {
  @Input()
  actualizarCliente!: any;

  constructor(
    private servicioCliente: ClienteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.servicioCliente.findById(id).subscribe(c => {
        this.actualizarCliente = c;
      });
    })
  }
}
