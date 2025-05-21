import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/modelo/admin';
import { UserEntity } from 'src/app/modelo/UserEntity';
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

    user:UserEntity = {
      id: -1,
      username: "",
      password: "",
      roles: []
    };

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
      else {
        this.user.username = c.username;
        this.user.password = this.admin.celular;
        this.servicioAdmin.login(this.user).subscribe({
        next: (data) => {
          localStorage.setItem('token', String(data)); // Guardar el token
          this.router.navigate(['/admin/dashboard/veterinarios/mostrar-veterinarios']); // Navegar al dashboard
        },
        error: (error) => {
          this.msgError = 'Error al iniciar sesión';
      }
    });
  }
    });
  }

    
}
