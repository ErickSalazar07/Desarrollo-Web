import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Mascota } from 'src/app/modelo/mascota';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { Location } from '@angular/common'

@Component({
  selector: 'app-formulario-mascota',
  templateUrl: './formulario-mascota.component.html',
  styleUrls: ['./formulario-mascota.component.css']
})
export class FormularioMascotaComponent implements OnInit {

  @Input()
  mascota!: Mascota;
  clienteEncontrado: boolean = true;

  constructor(
    private servicioMascota: MascotaService,
    private servicioCliente: ClienteService,
    private router: Router,
    private location:Location
  ) {}

  ngOnInit(): void { }

  submitMascota() {
    this.servicioCliente.findByCedula(this.mascota.cedulaCliente).
    subscribe(c => {
      if(c === null) {
        this.clienteEncontrado = false;
        return;
      }

      if(this.mascota.id === -1) {
        this.mascota.estadoActivo = false;
        this.servicioMascota.addMascota(this.mascota).subscribe({
            complete: () => this.location.back()
          }
        )
      } else {
        this.servicioMascota.updateMascota(this.mascota).subscribe({
          complete: () => this.location.back()
        });
      }
      this.clienteEncontrado = true;
    });
  }
}
