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
    this.servicioPrincipal.login(this.usuario).subscribe(
      (data) => {
        localStorage.setItem("token",String(data));
        this.servicioPrincipal.obtenerUserEntityActivo().subscribe({
          next: (usrEntity) => this.redirigirPorRol(usrEntity.roles.at(0)?.name),
          error: (err) => this.msgError = "Error: Usuario no valido."
        });
      }
    );
  }

  redirigirPorRol(rol?:string) {
    switch(rol) {
      case "VETERINARIO":
        this.router.navigate(["/veterinario/dashboard"]);
      break;
      case "ADMINISTRADOR":
        console.log("ADMINISTRADOR")
        this.router.navigate(["/admin/dashboard"]);
      break;
      case "CLIENTE":
        console.log("CLIENTE")
        this.router.navigate(["/cliente/dashboard"]);
      break;
      default: this.msgError = "Error: Usuario no valido."
    }
  }

  // logear() {
  //   this.servicioCliente.findByCedula(this.cliente.cedula).
  //   subscribe(c => {
  //     if(c == null || this.cliente.correo !== c.correo)  {
  //       this.msgError = "Datos incorrectos";
  //       return;
  //     }
  //     else {
  //       this.user.username = c.correo;
  //       this.user.password = this.cliente.cedula;
  //       this.servicioCliente.login(this.user).subscribe({
  //       next: (data) => {
  //         localStorage.setItem('token', String(data)); // Guardar el token
  //         this.router.navigate(['/cliente/dashboard/mostrar-cliente']); // Navegar al dashboard
  //       },
  //       error: (error) => {
  //         this.msgError = 'Error al iniciar sesión';
  //     }
  //   });
  // }
  //   });
  // }
}
