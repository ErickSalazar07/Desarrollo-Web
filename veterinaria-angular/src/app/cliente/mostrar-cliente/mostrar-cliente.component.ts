import { Component, Input, OnInit } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/servicio/cliente.service';


@Component({
  selector: 'app-mostrar-cliente',
  templateUrl: './mostrar-cliente.component.html',
  styleUrls: ['./mostrar-cliente.component.css']
})
export class MostrarClienteComponent implements OnInit{
  cliente!: Cliente;

  constructor(private http: HttpClient, private route: ActivatedRoute, private service: ClienteService) { }
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam !== null) {
      const id = Number(idParam);
      this.service.findById(id).subscribe(cliente => {
        this.cliente = cliente;
      });
    } else {
      console.error('ID no encontrado en la ruta');
    }
  }
  
}
