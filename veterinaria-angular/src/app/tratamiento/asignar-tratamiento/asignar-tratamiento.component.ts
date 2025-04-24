import { Component, OnInit } from '@angular/core';
import { TratamientoService } from 'src/app/servicio/tratamiento.service';
import { MascotaService } from 'src/app/servicio/mascota.service';
import { DrogaService } from 'src/app/servicio/droga.service';
import { Tratamiento } from 'src/app/modelo/tratamiento';
import { Mascota } from 'src/app/modelo/mascota';
import { Droga } from 'src/app/modelo/droga';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';
import { Veterinario } from 'src/app/modelo/veterinario';

@Component({
  selector: 'app-asignar-tratamiento',
  templateUrl: './asignar-tratamiento.component.html',
  styleUrls: ['./asignar-tratamiento.component.css']
})
export class AsignarTratamientoComponent implements OnInit {
  tratamiento: Tratamiento = {
    id: 0,
    nombreTratamiento: '',
    fecha: new Date(),
    drogaAsignada: { id: 0, nombre: '', precioCompra: 0, precioVenta: 0, unidadDisponible: 0, unidadVendida: 0 },
    mascota: { 
      id: 0, 
      nombre: '', 
      raza: '', 
      edad: 0, 
      peso: 0, 
      foto: '', 
      estadoActivo: true,  // Agregado
      cedulaCliente: ''   // Agregado
    },
    veterinario: { id: 0, nombre: '', cedula: '', contrasena: '', especialidad: '', activo: true, foto: '' }
  };

  mascotas: Mascota[] = [];
  drogas: Droga[] = []; 
  veterinarios: Veterinario[] = [];

  constructor(
    private tratamientoService: TratamientoService,
    private mascotaService: MascotaService,
    private drogaService: DrogaService,   
    private veterinarioService: VeterinarioService
  ) {}

  ngOnInit(): void {
    // Obtener la lista de mascotas y drogas desde sus respectivos servicios
    this.mascotaService.findAll().subscribe((mascotas) => {
      this.mascotas = mascotas;
    });

    this.drogaService.findAll().subscribe((drogas) => {
      this.drogas = drogas;
    });   

    this.veterinarioService.findAll().subscribe((veter) => {
      this.veterinarios = veter;
 
    });
  }

  onSubmit(): void {
    // Buscar el objeto completo de la mascota, droga y veterinario antes de enviar
    const selectedMascota = this.mascotas.find(mascota => mascota.id === this.tratamiento.mascota.id);
    const selectedDroga = this.drogas.find(droga => droga.id === this.tratamiento.drogaAsignada.id);
    const selectedVeterinario = this.veterinarios.find(veterinario => veterinario.id === this.tratamiento.veterinario.id);
  
    // Asignar los objetos completos al tratamiento
    if (selectedMascota) {
      this.tratamiento.mascota = selectedMascota;
    }
  
    if (selectedDroga) {
      this.tratamiento.drogaAsignada = selectedDroga;
    }
  
    if (selectedVeterinario) {
      this.tratamiento.veterinario = selectedVeterinario;
    }
     
    console.log(this.tratamiento); // Verifica los datos antes de enviarlos
    console.log(this.tratamiento.drogaAsignada)  
    console.log(this.tratamiento.mascota)
    console.log(this.tratamiento.veterinario)
    // Enviar el tratamiento con los objetos completos
    this.tratamientoService.addTratamiento(this.tratamiento).subscribe(
      (response) => {
        alert('Tratamiento asignado correctamente');
        console.log(response);
      },
      (error) => {
        alert('Error al asignar el tratamiento');
        console.error(error);
      }
    );
  }
  
}
