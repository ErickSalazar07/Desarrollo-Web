import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mascota } from 'src/app/modelo/mascota';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';

@Component({
  selector: 'app-mostrar-mascota',
  templateUrl: './mostrar-mascota.component.html',
  styleUrls: ['./mostrar-mascota.component.css']
})
export class MostrarMascotaComponent {

  mascota!: Mascota;
  tratamientos!: Tratamiento[];

  constructor(
    private servicioMascota: MascotaService,
    private servicioTratamiento: TratamientoService,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.servicioMascota.findById(id).subscribe(mascota => {
        this.mascota = mascota
        this.servicioTratamiento.findByMascotaId(this.mascota.id).
        subscribe(tratamientos => this.tratamientos = tratamientos)
      });
    })
  }
  volver() {
    window.history.back(); // Función para volver a la página anterior
  }
}


