import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { Cliente } from 'src/app/modelo/cliente';


@Component({
  selector: 'app-dashboard-cliente',
  templateUrl: './dashboard-cliente.component.html',
  styleUrls: ['./dashboard-cliente.component.css']
})
export class DashboardClienteComponent {
  cliente!: Cliente;
  constructor(private http: HttpClient, private route: ActivatedRoute,
      private servicioCliente: ClienteService, private servicioMascota: MascotaService) { }
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam !== null) {
      const id = Number(idParam);
      this.servicioCliente.findById(id).subscribe(cliente => {
        this.cliente = cliente;
      });
    } else {
      console.error('ID no encontrado en la ruta');
    }
  }
}
