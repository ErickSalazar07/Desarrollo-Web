import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-mostrar-mascota',
  templateUrl: './mostrar-mascota.component.html',
  styleUrls: ['./mostrar-mascota.component.css']
})
export class MostrarMascotaComponent {
  mascota?: any

  constructor(
    private servicioMascota: MascotaService,
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
      });
    })
  }
  volver() {
    window.history.back(); // Función para volver a la página anterior
  }
}


