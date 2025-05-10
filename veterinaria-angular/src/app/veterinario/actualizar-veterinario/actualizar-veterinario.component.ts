import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Veterinario } from 'src/app/modelo/veterinario';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-actualizar-veterinario',
  templateUrl: './actualizar-veterinario.component.html',
  styleUrls: ['./actualizar-veterinario.component.css']
})
export class ActualizarVeterinarioComponent {

  actualizarVeterinario!: Veterinario;

  constructor(
    private servicioVeterinario: VeterinarioService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.servicioVeterinario.findById(Number(params.get("id"))).
      subscribe(m => this.actualizarVeterinario = m)
    });
  }
}
