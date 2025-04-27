import { Component, OnInit } from '@angular/core';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { DrogaService } from 'src/app/servicio/droga.service';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';
import { Mascota } from 'src/app/modelo/mascota';
import { Droga } from 'src/app/modelo/droga';
import { Veterinario } from 'src/app/modelo/veterinario';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { TratamientoDTO } from 'src/app/modelo/tratamientoDTO';
import { Location } from '@angular/common';

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

  mascotas: Mascota[] = [];
  drogas: Droga[] = [];
  veterinarios: Veterinario[] = [];

  errorMensaje: string = '';

  constructor(
    private tratamientoService: TratamientoService,
    private mascotaService: MascotaService,
    private drogaService: DrogaService,
    private veterinarioService: VeterinarioService,
    private location:Location
  ) {}

  ngOnInit(): void {
    this.mascotaService.findAll().subscribe((mascotas) => this.mascotas = mascotas);
    this.drogaService.findAll().subscribe((drogas) => this.drogas = drogas);
    this.veterinarioService.findAll().subscribe((veter) => this.veterinarios = veter);
  }

  onSubmit(): void {
    this.errorMensaje = '';
    this.tratamientoDTO.mascotaID = Number(this.tratamientoDTO.mascotaID)   
    this.tratamientoDTO.drogaAsignadaID = Number(this.tratamientoDTO.drogaAsignadaID) 
    const mascotaExiste = this.mascotas.find(m => m.id === this.tratamientoDTO.mascotaID);
    const drogaExiste = this.drogas.find(d => d.id === this.tratamientoDTO.drogaAsignadaID);
    const veterinarioExiste = this.veterinarios.find(v => v.cedula === this.tratamientoDTO.veterinaroCedula);
    console.log(this.tratamientoDTO)
    if (!mascotaExiste || !drogaExiste || !veterinarioExiste) {
      let errores = [];
      if (!mascotaExiste) errores.push('la mascota');
      if (!drogaExiste) errores.push('la droga');
      if (!veterinarioExiste) errores.push('el veterinario');
      this.errorMensaje = `Error: No se encontró ${errores.join(', ')} seleccionado(a).`;
      return;
    }

    if(drogaExiste.unidadDisponible <= 0){
      this.errorMensaje = `Error: No hay unidades disponibles de ${drogaExiste.nombre}`;
      return;
    }

    drogaExiste.unidadDisponible--;
    drogaExiste.unidadVendida++;

    this.drogaService.updateDroga(drogaExiste).subscribe();

    this.tratamientoService.addTratamiento(this.tratamientoDTO).subscribe(
      (response) => {
        console.log(response);
        this.location.back();
      },
      (error) => {
        this.errorMensaje = 'Ocurrió un error al asignar el tratamiento.';
        console.error(error);
      }
    );
  }
}
