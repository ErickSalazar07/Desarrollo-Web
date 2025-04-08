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

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'cliente/clientes', component: MostrarClientesComponent},
  {path: 'cliente/add', component: CrearClienteComponent},
  {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
  {path: 'mascota/mascotas', component: MostrarMascotasComponent},
  {path: 'mascota/add', component: CrearMascotaComponent},
  {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
  {path: 'mascota/update/:id', component: ActualizarMascotaComponent},
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: '**', component: PagNoEncontradaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
