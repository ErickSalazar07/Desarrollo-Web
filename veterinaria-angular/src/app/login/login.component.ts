import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { UserEntity } from 'src/app/modelo/UserEntity';
import { PrincipalService } from '../servicio/principal.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario:UserEntity = {
    id: -1,
    username: "",
    password: "",
    roles: []
  };

  msgError:string = "";

  constructor(
    private servicioPrincipal:PrincipalService,
    private router: Router
  ) {}

  login() {
    this.servicioPrincipal.login(this.usuario).subscribe({
      next: (data) => {
        localStorage.setItem("token",String(data));
        this.servicioPrincipal.obtenerUserEntityActivo().subscribe({
          next: (usrEntity) => {
            let rol = usrEntity.roles.at(0)?.name;
            localStorage.setItem("rolActivo",String(rol));
            this.redirigirPorRol(rol);
          },
          error: (err) => this.msgError = "Error: Usuario no valido."
        });
      },
      error: (err) => this.msgError = "Error: Usuario no valido."
    });
  }

  redirigirPorRol(rol?:string) {
    switch(rol) {
      case "VETERINARIO":
        this.router.navigate(["/veterinario/dashboard"]);
      break;
      case "ADMINISTRADOR":
        this.router.navigate(["/admin/dashboard"]);
      break;
      case "CLIENTE":
        this.router.navigate(["/cliente/dashboard"]);
      break;
      default: this.msgError = "Error: Usuario no valido.";
    }
  }
}
