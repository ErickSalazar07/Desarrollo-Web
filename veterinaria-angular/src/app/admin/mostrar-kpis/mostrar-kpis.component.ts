import { Component } from '@angular/core';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { DrogaService } from 'src/app/servicio/droga.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-mostrar-kpis',
  templateUrl: './mostrar-kpis.component.html',
  styleUrls: ['./mostrar-kpis.component.css']
})
export class MostrarKpisComponent {

  numTratamientosUltimoMes:number = 0;
  numTratamientosTipoMedicamento!:number;
  numVeterinariosActivos:number = 0;
  numVeterinariosInactivos:number = 0;
  numMascotas:number = 0;
  numMascotasActivas:number = 0;
  ventasTotales:number = 0.0;
  gananciasTotales:number = 0.0;
  top3Tratamientos:Tratamiento[] | null = null;

  constructor(
    private veterinarioServicio:VeterinarioService,
    private tratamientoServicio:TratamientoService,
    private mascotaServicio:MascotaService,
    private drogaServicio:DrogaService
  ) { }

  ngOnInit() {
    this.tratamientoServicio.obtenerNumTratamientosUltimoMes().subscribe(n => {
      this.numTratamientosUltimoMes = n;
    });

    this.tratamientoServicio.obtenerTop3TratamientosMasUnidadVendida().subscribe(top3 => {
      this.top3Tratamientos = top3;
    })

    this.veterinarioServicio.obtenerNumVeterinariosActivos().subscribe(n => {
      this.numVeterinariosActivos = n;
    })

    this.veterinarioServicio.obtenerNumVeterinariosInactivos().subscribe(n => {
      this.numVeterinariosInactivos = n;
    })

    this.mascotaServicio.obtenerNumMascotas().subscribe(n => {
      this.numMascotas = n;
    })

    this.mascotaServicio.obtenerNumMascotasActivas().subscribe(n => {
      this.numMascotasActivas = n;
    })

    this.drogaServicio.obtenerTotalGanancias().subscribe(ganancias => {
      this.gananciasTotales = ganancias;
    })

    this.drogaServicio.obtenerTotalVentas().subscribe(ventas => {
      this.ventasTotales = ventas;
    })
  }
}
