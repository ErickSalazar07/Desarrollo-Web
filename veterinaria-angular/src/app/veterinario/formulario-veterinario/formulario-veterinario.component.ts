import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Veterinario } from 'src/app/modelo/veterinario';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-formulario-veterinario',
  templateUrl: './formulario-veterinario.component.html',
  styleUrls: ['./formulario-veterinario.component.css']
})
export class FormularioVeterinarioComponent {

  @Input()
  veterinario!: Veterinario;
  veterinarioEncontrado: boolean = true;

  constructor(
    private servicioVeterinario: VeterinarioService,
    private router: Router
  ) {}

  ngOnInit(): void { }

  submitVeterinario() {
    this.servicioVeterinario.findByCedula(this.veterinario.cedula).
    subscribe(c => {
      if(c === null) {
        this.veterinarioEncontrado = false;
        return;
      }

      if(this.veterinario.id === -1) {
        this.servicioVeterinario.addVeterinario(this.veterinario).subscribe({
            complete: () => this.router.navigate(['/admin/dashboard/veterinarios/mostrar-veterinarios'])
          }
        )
      } else {
        this.servicioVeterinario.updateVeterinarioByCedula(this.veterinario).subscribe({
          complete: () => this.router.navigate(['/admin/dashboard/veterinarios/mostrar-veterinarios'])
        });
      }
      this.veterinarioEncontrado = true;
    });
  }
}
