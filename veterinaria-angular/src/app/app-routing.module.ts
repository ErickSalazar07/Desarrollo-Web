import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './admin/home/home.component';
import { MostrarClientesComponent } from './cliente/mostrar-clientes/mostrar-clientes.component';
import { MostrarMascotasComponent } from './mascota/mostrar-mascotas/mostrar-mascotas.component';
import { PagNoEncontradaComponent } from './error/pag-no-encontrada/pag-no-encontrada.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'cliente', component: MostrarClientesComponent},
  {path: 'mascota', component: MostrarMascotasComponent},
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  { path: '**', component: PagNoEncontradaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
