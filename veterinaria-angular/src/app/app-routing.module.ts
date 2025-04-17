import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './admin/home/home.component';
import { MostrarClientesComponent } from './cliente/mostrar-clientes/mostrar-clientes.component';
import { MostrarMascotasComponent } from './mascota/mostrar-mascotas/mostrar-mascotas.component';
import { PagNoEncontradaComponent } from './error/pag-no-encontrada/pag-no-encontrada.component';
import { MostrarMascotaComponent } from './mascota/mostrar-mascota/mostrar-mascota.component';
import { MostrarClienteComponent } from './cliente/mostrar-cliente/mostrar-cliente.component';
import { CrearMascotaComponent } from './mascota/crear-mascota/crear-mascota.component';
import { ActualizarMascotaComponent } from './mascota/actualizar-mascota/actualizar-mascota.component';
import { CrearClienteComponent } from './cliente/crear-cliente/crear-cliente.component';
import { ActualizarClienteComponent } from './cliente/actualizar-cliente/actualizar-cliente.component';
import { DashboardComponent } from './veterinario/dashboard/dashboard.component';
import { LoginClienteComponent } from './cliente/login-cliente/login-cliente.component';
import { DashboardClienteComponent } from './cliente/dashboard-cliente/dashboard-cliente.component';
import { LoginVeterinarioComponent } from './veterinario/login-veterinario/login-veterinario.component';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';

const routes: Routes = [

  {path: 'veterinario/dashboard', component: DashboardComponent, // <- Este tiene el router-outlet
  children: [
    { path: 'mascota/mascotas', component: MostrarMascotasComponent },
    {path: 'cliente/clientes', component: MostrarClientesComponent},
    {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
    {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
  ]
  },

  {path: 'cliente/dashboard/:id', component: DashboardClienteComponent ,
  children: [
    {path: 'mostrar-cliente/:id', component: MostrarClienteComponent},
  ]
},
  {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
  {path: 'home', component: HomeComponent},
  {path: 'cliente/login', component: LoginClienteComponent},
  {path: 'admin/login', component: LoginAdminComponent},
  {path: 'cliente/add', component: CrearClienteComponent},
  {path: 'cliente/clientes', component: MostrarClientesComponent},
  {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
  {path: 'cliente/update/:id', component: ActualizarClienteComponent},
  {path: 'mascota/mascotas', component: MostrarMascotasComponent},
  {path: 'mascota/add', component: CrearMascotaComponent},
  {path: 'mascota/update/:id', component: ActualizarMascotaComponent},
  {path: 'veterinario/login', component: LoginVeterinarioComponent},
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: '**', component: PagNoEncontradaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
