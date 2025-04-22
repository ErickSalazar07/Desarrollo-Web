import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Veterinario } from '../modelo/veterinario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VeterinarioService {


  constructor(private http: HttpClient) { }

  veterinarios: Veterinario[] = [];

  URL_ROOT:string = "http://localhost:8090/veterinario";

  findByCedula(cedula: string): Observable<Veterinario> {
    return this.http.get<Veterinario>(this.URL_ROOT + `/get-veterinario-cedula/${cedula}`);
  } 
  findAll(): Observable<Veterinario[]> {
    return this.http.get<Veterinario[]>(this.URL_ROOT + `/veterinarios`);
  } 

  cambiarEstadoByCedula(cedula: string): Observable<Veterinario> {
    return this.http.put<Veterinario>(this.URL_ROOT + `/update-estado/${cedula}`, {});
  }

  addVeterinario(veterinario: Veterinario): Observable<any> {
    return this.http.post(this.URL_ROOT + `/add`, veterinario);
  }

  updateVeterinarioById(veterinario: Veterinario): Observable<Veterinario> {
    return this.http.put<Veterinario>(this.URL_ROOT + `/update-id/${veterinario.id}`, veterinario);
  }

  findById(id: number): Observable<Veterinario> {
    return this.http.get<Veterinario>(this.URL_ROOT + `/get-veterinario/${id}`);
  }

  updateVeterinarioByCedula(veterinario: Veterinario): Observable<Veterinario> {
    return this.http.put<Veterinario>(this.URL_ROOT + `/update-cc/${veterinario.cedula}`, veterinario);
  }
}
