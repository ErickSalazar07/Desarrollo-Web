import { Component, Input, OnInit } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';


@Component({
  selector: 'app-mostrar-cliente',
  templateUrl: './mostrar-cliente.component.html',
  styleUrls: ['./mostrar-cliente.component.css']
})
export class MostrarClienteComponent implements OnInit{
  cliente!: Cliente;
  mascotas!: Mascota[];

  constructor(private http: HttpClient,
    private route: ActivatedRoute,
    private servicioCliente: ClienteService,
    private servicioMascota: MascotaService) { }

  ngOnInit(): void {
    const idCliente = this.route.snapshot.paramMap.get('id');

    if (idCliente) {
      // Si viene un id por la ruta, se está accediendo desde el rol veterinario (u otro admin)
      this.servicioCliente.findByCedula(idCliente).subscribe({
        next: (cliente) => {
          this.cliente = cliente;
          this.servicioMascota.findMascotasByClienteCedula(cliente.cedula)
            .subscribe(mascotas => this.mascotas = mascotas);
        },
        error: (err) => {
          console.error('Error al obtener el cliente por ID:', err);
        }
      });
    } else {
      // Si no viene un id, se trata del cliente autenticado
      this.servicioCliente.clienteActual().subscribe({
        next: (cliente) => {
          this.cliente = cliente;
          this.servicioMascota.findMascotasByClienteAut()
            .subscribe(mascotas => this.mascotas = mascotas);
        },
        error: (err) => {
          console.error('Error al obtener el cliente autenticado:', err);
        }
      });
    }
  }

}
