import { Component } from '@angular/core';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';

@Component({
  selector: 'app-mostrar-tratamientos',
  templateUrl: './mostrar-tratamientos.component.html',
  styleUrls: ['./mostrar-tratamientos.component.css']
})
export class MostrarTratamientosComponent {
  tratamientos!: Tratamiento[];

  constructor(
    private tratamientoServicio: TratamientoService 
  ) { }

  ngOnInit() {
    this.tratamientoServicio.findAll().subscribe(tratamientos => {
      this.tratamientos = tratamientos;
    })
  }
}
