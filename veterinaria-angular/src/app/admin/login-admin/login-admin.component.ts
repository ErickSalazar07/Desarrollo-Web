import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/modelo/admin';
import { AdminService} from 'src/app/servicio/admin.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent {

  admin:Admin = {
    username: "",
    id: -1,
    celular: "",
    nombre: "",
    correo: "",
  }

  msgError:string = "";

  constructor(
    private servicioAdmin:AdminService,
    private router: Router
  ) {}

  logearAdmin() {
    this.servicioAdmin.findByUsername(this.admin.username).
    subscribe(c => {
      if(c == null || this.admin.celular !== c.celular)  {
        this.msgError = "Datos incorrectos";
        return;
      }
      this.router.navigate([`/admin/dashboard/veterinarios/mostrar-veterinarios`]);
    });
  }
    
}
