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

}
