import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserEntity } from 'src/app/modelo/UserEntity';
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
  }

    user: UserEntity = {
      id:-1,
      username: "",
      password: ""
    }
  

  msgError:string = "";

  constructor(
    private servicioVeterinario: VeterinarioService,
    private router: Router
  ) {}

  logearVeterinario() {
    this.user.username = this.veterinario.user.username;
    this.user.password = this.veterinario.user.password;
    this.servicioVeterinario.findByCedula(this.user.username).subscribe(c => {
      if(!c) { 
        this.msgError = "Datos incorrectos";
        return;
      }
      else {
        this.servicioVeterinario.login(this.user).subscribe({
        next: (data) => {
          localStorage.setItem('token', String(data)); // Guardar el token
          this.router.navigate(['/veterinario/dashboard/mascota/mascotas']); // Navegar al dashboard
        },
        error: (error) => {
          this.msgError = 'Error al iniciar sesión';
      }
    });
  }
    });
  }
}