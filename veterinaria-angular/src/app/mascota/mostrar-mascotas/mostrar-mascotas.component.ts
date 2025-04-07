import { Component } from '@angular/core';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';
import {HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mostrar-mascotas',
  templateUrl: './mostrar-mascotas.component.html',
  styleUrls: ['./mostrar-mascotas.component.css']
})
export class MostrarMascotasComponent {

  mascotaSeleccionadaMostrar!: Mascota;
  mascotaSeleccionadaModificar?: Mascota | null =  null;
  mascotas: Mascota[]  = [];
  showFormulario = false;

  constructor(private http: HttpClient, private service: MascotaService, private router: Router) { }

  ngOnInit() {
    this.service.findAll().subscribe(mascotas => {
      this.mascotas = mascotas;
    });
  }

  crearMascota() {
    this.showFormulario = true; 
  }
  formularioCerrado() {
    this.mascotaSeleccionadaModificar = null; 
    this.showFormulario = false;  
  }


  eliminarMascota(mascota: Mascota) {
    this.service.deleteById(mascota.id).subscribe(() => {
      this.service.findAll().subscribe(mascotas => {
        this.mascotas = mascotas;
      })
    })
  }

  mostrarMascota(mascota: Mascota) {
    this.router.navigate(['/mascota/mostrar-mascota', mascota.id]);
  }

  actualizarMascota(mascota: Mascota) {
    this.mascotaSeleccionadaModificar = mascota;
    this.showFormulario = true;  
  }

  toggleFormulario() {
    this.showFormulario = !this.showFormulario;
  }
}
