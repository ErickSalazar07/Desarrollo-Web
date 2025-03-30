import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Mascota } from 'src/app/modelo/mascota';
import { MascotaService } from 'src/app/servicio/mascota.service';

@Component({
  selector: 'app-formulario-mascota',
  templateUrl: './formulario-mascota.component.html',
  styleUrls: ['./formulario-mascota.component.css']
})
export class FormularioMascotaComponent implements OnInit {
  @Input() mascota!: Mascota;
  @Output() formularioCerrado = new EventEmitter<void>();  // Evento para cerrar el formulario

  mascotaForm: Mascota = this.crearNuevaMascota();
  esEdicion: boolean = false;

  constructor(
    private servicioMascota: MascotaService,
    private router: Router
  ) {}

  ngOnInit() {
    if (this.mascota && this.mascota.nombre !== '') {
      this.mascotaForm = { ...this.mascota };
      this.esEdicion = true;
    } else {
      this.mascotaForm = this.crearNuevaMascota();
      this.esEdicion = false;
    }
  }

  guardarMascota() {
    if (this.esEdicion) {
      this.servicioMascota.updateMascota(this.mascotaForm)
      this.router.navigate(['/mascota/mascotas']);
    } else {
      this.servicioMascota.guardarMascota(this.mascotaForm);
      this.router.navigate(['/mascota/mascotas']);
    }
    this.servicioMascota.findAll();
    this.formularioCerrado.emit();
    this.router.navigate(['/mascota/mascotas']);
  }

  private crearNuevaMascota(): Mascota {
    return {
      id: -1,
      nombre: '',
      raza: '',
      edad: 0,
      peso: 0,
      cedulaCliente: '',
      foto: '',
      estadoActivo: false
    };
  }
}
