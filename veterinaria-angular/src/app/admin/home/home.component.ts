import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    private router: Router
  ) {}

  ngOnInit() {
    let rol = localStorage.getItem("rolActivo");

    if(rol) this.redirigirPorRol(rol);
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
    }
  }
}
