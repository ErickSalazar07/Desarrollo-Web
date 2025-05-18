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
import { DashboardAdminComponent } from './admin/dashboard-admin/dashboard-admin.component';
import { MostrarVeterinariosComponent } from './veterinario/mostrar-veterinarios/mostrar-veterinarios.component';
import { CrearVeterinarioComponent } from './veterinario/crear-veterinario/crear-veterinario.component';
import { ActualizarVeterinarioComponent } from './veterinario/actualizar-veterinario/actualizar-veterinario.component';
import { MostrarTratamientosComponent } from './tratamiento/mostrar-tratamientos/mostrar-tratamientos.component';
import { AsignarTratamientoComponent } from './tratamiento/asignar-tratamiento/asignar-tratamiento.component';
import { MostrarKpisComponent } from './admin/mostrar-kpis/mostrar-kpis.component';
import { MostrarNumItemsComponent } from './utils/mostrar-num-items/mostrar-num-items.component';
import { MostrarValorDineroComponent } from './utils/mostrar-valor-dinero/mostrar-valor-dinero.component';
import { MostrarListaItemsComponent } from './utils/mostrar-lista-items/mostrar-lista-items.component';
import { MostrarDrogasComponent } from './droga/mostrar-drogas/mostrar-drogas.component';
import { TratamientosVeterinarioComponent } from './tratamiento/tratamientos-veterinario/tratamientos-veterinario.component';

const routes: Routes = [

  {path: 'veterinario/dashboard', component: DashboardComponent, // <- Este tiene el router-outlet
  children:
  [
    {path: 'mascota/mascotas', component: MostrarMascotasComponent},
    {path: 'cliente/clientes', component: MostrarClientesComponent},
    {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
    {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
    {path: 'tratamiento/tratamientos', component: MostrarTratamientosComponent},
    {path: 'tratamiento/asignar-tratamiento', component: AsignarTratamientoComponent},
    {path: 'droga/drogas', component: MostrarDrogasComponent},
    {path: 'tratamientos-veterinario', component: TratamientosVeterinarioComponent}
  ]
  },

  {path: 'cliente/dashboard', component: DashboardClienteComponent ,
  children:
  [
    {path: 'mostrar-cliente', component: MostrarClienteComponent},
  ]
  },
  
  {path: 'admin/dashboard', component: DashboardAdminComponent,
    children: [
      {path: 'mascota/mascotas', component: MostrarMascotasComponent},
      {path: 'cliente/clientes', component: MostrarClientesComponent},
      {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
      {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
      {path: 'veterinarios/mostrar-veterinarios', component: MostrarVeterinariosComponent},
      {path: 'mostrar-kpis', component: MostrarKpisComponent},
      {path: 'droga/drogas', component: MostrarDrogasComponent}
    ]
  },
  {path: 'admin/login', component: LoginAdminComponent},
  {path: 'home', component: HomeComponent},
  {path: 'cliente/login', component: LoginClienteComponent},
  {path: 'cliente/add', component: CrearClienteComponent},
  {path: 'cliente/clientes', component: MostrarClientesComponent},
  {path: 'cliente/mostrar-cliente/:id', component: MostrarClienteComponent},
  {path: 'cliente/update/:id', component: ActualizarClienteComponent},
  {path: 'mascota/mascotas', component: MostrarMascotasComponent},
  {path: 'mascota/mostrar-mascota/:id', component: MostrarMascotaComponent},
  {path: 'mascota/add', component: CrearMascotaComponent},
  {path: 'mascota/update/:id', component: ActualizarMascotaComponent},
  {path: 'veterinario/login', component: LoginVeterinarioComponent},
  {path: 'veterinario/add', component: CrearVeterinarioComponent},
  {path: 'veterinario/update/:id', component: ActualizarVeterinarioComponent},
  {path: 'tratamiento/asignar-tratamiento', component: AsignarTratamientoComponent},
  {path: 'mostrar-num-items/:nombre-item/:cantidad', component: MostrarNumItemsComponent},
  {path: 'mostrar-valor-dinero/:txt-msg/:dinero', component: MostrarValorDineroComponent},
  {path: 'mostrar-lista-items/:tipo/:mensaje', component: MostrarListaItemsComponent},
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: '**', component: PagNoEncontradaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
