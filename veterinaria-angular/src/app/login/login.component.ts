import { Route, Router } from '@angular/router';
import { Component, Output } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';
import { UserEntity } from 'src/app/modelo/UserEntity';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.component.html',
  styleUrls: ['./login-cliente.component.css']
})
export class LoginClienteComponent {

  user:UserEntity = {
    id: -1,
    username: "",
    password: ""
  };

  msgError:string = "";

  constructor(
    private servicioCliente:ClienteService,
    private router: Router
  ) {}

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
