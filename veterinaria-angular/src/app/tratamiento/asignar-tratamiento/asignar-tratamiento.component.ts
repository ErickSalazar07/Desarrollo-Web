import { Component, OnInit } from '@angular/core';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { DrogaService } from 'src/app/servicio/droga.service';
import { Mascota } from 'src/app/modelo/mascota';
import { Droga } from 'src/app/modelo/droga';
import { Veterinario } from 'src/app/modelo/veterinario';
import { TratamientoDTO } from 'src/app/modelo/tratamientoDTO';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-asignar-tratamiento',
  templateUrl: './asignar-tratamiento.component.html',
  styleUrls: ['./asignar-tratamiento.component.css']
})
export class AsignarTratamientoComponent implements OnInit {

  tratamientoDTO :TratamientoDTO= {  
    id : -1,
    nombreTratamiento: '',
    fecha: '',
    drogaAsignadaID: 0,
    mascotaID: 0,
    veterinaroCedula: ''
  };

  veterinario!:Veterinario;

  mascotas: Mascota[] = [];
  drogas: Droga[] = [];
  veterinarios: Veterinario[] = [];

  errorMensaje: string = '';

  constructor(
    private tratamientoService: TratamientoService,
    private veterinarioService: VeterinarioService,
    private mascotaService: MascotaService,
    private drogaService: DrogaService,
    private location:Location,
    private route:ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.veterinarioService.veterinarioActual().subscribe({
      next: (veterinario) => {
        this.veterinario = veterinario;
        console.log(this.veterinario.cedula);
      },
      error: (err) => {
        console.error('Error al obtener el veterinario:', err);
      }
    });
    this.mascotaService.findAll().subscribe((mascotas) => this.mascotas = mascotas);
    this.drogaService.findAll().subscribe((drogas) => this.drogas = drogas.filter(d => d.unidadDisponible > 0));
  }

  onSubmit(): void {
    this.errorMensaje = '';
    this.tratamientoDTO.mascotaID = Number(this.tratamientoDTO.mascotaID)   
    this.tratamientoDTO.drogaAsignadaID = Number(this.tratamientoDTO.drogaAsignadaID) 
    const mascotaExiste = this.mascotas.find(m => m.id === this.tratamientoDTO.mascotaID);
    const drogaExiste = this.drogas.find(d => d.id === this.tratamientoDTO.drogaAsignadaID);
    this.tratamientoDTO.veterinaroCedula = this.veterinario.cedula.toString();

    console.log(this.tratamientoDTO)
    if (!mascotaExiste || !drogaExiste) {
      let errores = [];
      if (!mascotaExiste) errores.push('la mascota');
      if (!drogaExiste) errores.push('la droga');
      this.errorMensaje = `Error: No se encontró ${errores.join(', ')} seleccionado(a).`;
      return;
    }

    if(drogaExiste.unidadDisponible <= 0){
      this.errorMensaje = `Error: No hay unidades disponibles de ${drogaExiste.nombre}`;
      return;
    }

    this.tratamientoService.addTratamiento(this.tratamientoDTO).subscribe({
      next: response => {
        console.log("Respuesta backend:",response);
        this.location.back();
      },
      error: e => {
        console.log("Error en la peticion:",e);
        this.errorMensaje = "Hubo un error al guardar el tratamiento.";
      }
    });
  }
}
