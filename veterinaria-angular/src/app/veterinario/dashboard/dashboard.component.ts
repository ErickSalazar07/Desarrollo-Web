import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Veterinario } from 'src/app/modelo/veterinario';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  veterinario!: Veterinario;
  constructor(private http: HttpClient, private route: ActivatedRoute,
      private servicioVeterinario: VeterinarioService) { }
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam !== null) {
      const cedula = idParam;
      this.servicioVeterinario.findByCedula(cedula).subscribe(veterinario => {
        this.veterinario = veterinario;
      });
    } else {
      console.error('ID no encontrado en la ruta');
    }
  }
}
