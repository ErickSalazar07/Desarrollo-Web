import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent {
// ESTO LO COPIÉ DE LOGIN-CLIENTE, PERO TENGO QUE CAMBIARLO PARA QUE SEAN ADMINISTRADORES
  cliente:Cliente = {
    nombre: "",
    id: -1,
    cedula: "",
    celular: "",
    correo: "",
  };

  msgError:string = "";

  constructor(
    private servicioCliente:ClienteService,
    private router: Router
  ) {}

  logearCliente() {
    this.servicioCliente.findByCedula(this.cliente.cedula).
    subscribe(c => {
      if(c == null || this.cliente.correo !== c.correo)  {
        this.msgError = "Datos incorrectos";
        return;
      }
      this.router.navigate([`/cliente/dashboard/${c.id}/mostrar-cliente/${c.id}`]);
    });
  }
    
}
