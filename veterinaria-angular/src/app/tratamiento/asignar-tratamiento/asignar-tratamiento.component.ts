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
    private location:Location,
    private route:ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.mascotaService.findAll().subscribe((mascotas) => this.mascotas = mascotas);
    this.drogaService.findAll().subscribe((drogas) => this.drogas = drogas);
    const cedulaVet = this.route.snapshot.paramMap.get('cedulaVeterinario');
    if(cedulaVet)
      this.tratamientoDTO.veterinaroCedula = cedulaVet;
  }

  onSubmit(): void {
    this.errorMensaje = '';
    this.tratamientoDTO.mascotaID = Number(this.tratamientoDTO.mascotaID)   
    this.tratamientoDTO.drogaAsignadaID = Number(this.tratamientoDTO.drogaAsignadaID) 
    const mascotaExiste = this.mascotas.find(m => m.id === this.tratamientoDTO.mascotaID);
    const drogaExiste = this.drogas.find(d => d.id === this.tratamientoDTO.drogaAsignadaID);
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

    this.tratamientoService.addTratamiento(this.tratamientoDTO).subscribe({complete: () => {this.location.back()}});
  }
}
