import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tratamiento } from '../modelo/tratamiento';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TratamientoService {

  constructor(private http:HttpClient) { }

  tratamientos: Tratamiento[] = [];
  URL_ROOT:string = "http://localhost:8090/tratamiento";

  findAll(): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(this.URL_ROOT + `/tratamientos`);
  }

  findById(id:number): Observable<Tratamiento> {
    return this.http.get<Tratamiento>(this.URL_ROOT + `/get-tratamiento/${id}`);
  }

  findByMascotaId(idMascota:number): Observable<Tratamiento[]> {
    return this.http.
    get<Tratamiento[]>(this.URL_ROOT + `/tratamientos-mascota/${idMascota}`);
  }

  addTratamiento(tratamiento:Tratamiento): Observable<any> {
    return this.http.post(this.URL_ROOT + `/add`,tratamiento);
  }

  updateTratamiento(tratamiento:Tratamiento): Observable<any> {
    return this.http.put(this.URL_ROOT + `/update/${tratamiento.id}`,tratamiento);
  }

  deleteById(id:number): Observable<any> {
    return this.http.delete(this.URL_ROOT + `/delete/${id}`);
  }
}
