import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-actualizar-mascota',
  templateUrl: './actualizar-mascota.component.html',
  styleUrls: ['./actualizar-mascota.component.css']
})
export class ActualizarMascotaComponent {

  actualizarMascota!: Mascota;

  constructor(
    private servicioMascota: MascotaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.servicioMascota.findById(Number(params.get("id"))).
      subscribe(m => this.actualizarMascota = m)
    });
  }
}
