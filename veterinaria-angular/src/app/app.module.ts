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
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CrearClienteComponent } from './cliente/crear-cliente/crear-cliente.component';
import { DashboardComponent } from './veterinario/dashboard/dashboard.component';
import { LoginClienteComponent } from './cliente/login-cliente/login-cliente.component';
import { DashboardClienteComponent } from './cliente/dashboard-cliente/dashboard-cliente.component';
import { LoginVeterinarioComponent } from './veterinario/login-veterinario/login-veterinario.component';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';
import { DashboardAdminComponent } from './admin/dashboard-admin/dashboard-admin.component';
import { MostrarVeterinariosComponent } from './veterinario/mostrar-veterinarios/mostrar-veterinarios.component';
import { FormularioVeterinarioComponent } from './veterinario/formulario-veterinario/formulario-veterinario.component';
import { CrearVeterinarioComponent } from './veterinario/crear-veterinario/crear-veterinario.component';
import { ActualizarVeterinarioComponent } from './veterinario/actualizar-veterinario/actualizar-veterinario.component';
import { CrearTratamientoComponent } from './tratamiento/crear-tratamiento/crear-tratamiento.component';
import { ActualizarTratamientoComponent } from './tratamiento/actualizar-tratamiento/actualizar-tratamiento.component';
import { AsignarTratamientoComponent } from './tratamiento/asignar-tratamiento/asignar-tratamiento.component';
import { MostrarTratamientosComponent } from './tratamiento/mostrar-tratamientos/mostrar-tratamientos.component';
import { MostrarKpisComponent } from './admin/mostrar-kpis/mostrar-kpis.component';
import { MostrarNumItemsComponent } from './utils/mostrar-num-items/mostrar-num-items.component';
import { MostrarValorDineroComponent } from './utils/mostrar-valor-dinero/mostrar-valor-dinero.component';
import { MostrarListaItemsComponent } from './utils/mostrar-lista-items/mostrar-lista-items.component';
import { MostrarDrogasComponent } from './droga/mostrar-drogas/mostrar-drogas.component';
import { TratamientosVeterinarioComponent } from './tratamiento/tratamientos-veterinario/tratamientos-veterinario.component';
import { AuthInterceptor } from './helpers/auth.interceptor';

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
    MostrarVeterinariosComponent,
    FormularioVeterinarioComponent,
    CrearVeterinarioComponent,
    ActualizarVeterinarioComponent,
    CrearTratamientoComponent,
    ActualizarTratamientoComponent,
    AsignarTratamientoComponent,
    MostrarTratamientosComponent,
    MostrarKpisComponent,
    MostrarNumItemsComponent,
    MostrarValorDineroComponent,
    MostrarListaItemsComponent,
    MostrarDrogasComponent,
    TratamientosVeterinarioComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule   
    

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
