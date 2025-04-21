import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MostrarClienteComponent } from './cliente/mostrar-cliente/mostrar-cliente.component';
import { MostrarClientesComponent } from './cliente/mostrar-clientes/mostrar-clientes.component';
import { ActualizarClienteComponent } from './cliente/actualizar-cliente/actualizar-cliente.component';
import { FormularioClienteComponent } from './cliente/formulario-cliente/formulario-cliente.component';
import { HomeComponent } from './admin/home/home.component';
import { PagNoEncontradaComponent } from './error/pag-no-encontrada/pag-no-encontrada.component';
import { ActualizarMascotaComponent } from './mascota/actualizar-mascota/actualizar-mascota.component';
import { FormularioMascotaComponent } from './mascota/formulario-mascota/formulario-mascota.component';
import { MostrarMascotaComponent } from './mascota/mostrar-mascota/mostrar-mascota.component';
import { MostrarMascotasComponent } from './mascota/mostrar-mascotas/mostrar-mascotas.component';
import { CrearMascotaComponent } from './mascota/crear-mascota/crear-mascota.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CrearClienteComponent } from './cliente/crear-cliente/crear-cliente.component';
import { DashboardComponent } from './veterinario/dashboard/dashboard.component';
import { LoginClienteComponent } from './cliente/login-cliente/login-cliente.component';
import { DashboardClienteComponent } from './cliente/dashboard-cliente/dashboard-cliente.component';
import { LoginVeterinarioComponent } from './veterinario/login-veterinario/login-veterinario.component';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';
import { DashboardAdminComponent } from './admin/dashboard-admin/dashboard-admin.component';
import { MostrarVeterinariosComponent } from './veterinario/mostrar-veterinarios/mostrar-veterinarios.component';

@NgModule({
  declarations: [
    AppComponent,
    MostrarClienteComponent,
    MostrarClientesComponent,
    ActualizarClienteComponent,
    FormularioClienteComponent,
    HomeComponent,
    PagNoEncontradaComponent,
    ActualizarMascotaComponent,
    FormularioMascotaComponent,
    MostrarMascotaComponent,
    MostrarMascotasComponent,
    CrearMascotaComponent,
    CrearClienteComponent,
    DashboardComponent,
    LoginClienteComponent,
    DashboardClienteComponent,
    LoginVeterinarioComponent,
    LoginAdminComponent,
    DashboardAdminComponent,
    MostrarVeterinariosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
