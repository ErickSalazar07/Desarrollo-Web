import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { Cliente } from 'src/app/modelo/cliente';
import { ClienteService } from 'src/app/servicio/cliente.service';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.component.html',
  styleUrls: ['./login-cliente.component.css']
})
export class LoginClienteComponent {
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
      if(c == null) {
        this.msgError = "El cliente no existe."
        return;
      }
      this.router.navigate(['/cliente/mostrar-cliente/',c.id]);
    });
  }
}
