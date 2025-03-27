import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
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

  constructor(private servicioMascota: MascotaService) {}

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
      this.servicioMascota.updateMascota(this.mascotaForm);
      
    } else {
      this.servicioMascota.guardarMascota(this.mascotaForm);
    }
    this.servicioMascota.findAll();
    this.formularioCerrado.emit(); 
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
