import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Veterinario } from 'src/app/modelo/veterinario';
import { VeterinarioService } from 'src/app/servicio/veterinario.service';

@Component({
  selector: 'app-login-veterinario',
  templateUrl: './login-veterinario.component.html',
  styleUrls: ['./login-veterinario.component.css']
})
export class LoginVeterinarioComponent {

  veterinario:Veterinario = {
    nombre: "",
    id: -1,
    cedula: "",
    contrasena: "",
    foto: "",
    especialidad: "",
    activo: false,
    user: {
      id: -1,
      username: "",
      password: ""
    }
  };

  msgError:string = "";

  constructor(
    private servicioVeterinario: VeterinarioService,
    private router: Router
  ) {}

  logearVeterinario() {
    this.servicioVeterinario.findByCedula(this.veterinario.user.username).subscribe(c => {
      /*if(c == null || this.veterinario.contrasena !== c.contrasena)  {
        this.msgError = "Datos incorrectos";
        return;
      }*/
      if(!c) { 
        this.msgError = "Datos incorrectos";
        return;
      }
      this.router.navigate([`/veterinario/dashboard/${c.user.username}/mascota/mascotas`]);
    });
  }
}
