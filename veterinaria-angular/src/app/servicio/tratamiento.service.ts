import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tratamiento } from '../modelo/tratamiento';
import { Observable } from 'rxjs';
import { TratamientoDTO } from '../modelo/tratamientoDTO';

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

  addTratamiento(tratamiento:TratamientoDTO): Observable<any> {
    console.log("IMPRIMIENDO DATOS DE TRATAMIENTO")
    console.log(tratamiento);
    return this.http.post(this.URL_ROOT + `/add`,tratamiento);
  }

  updateTratamiento(tratamiento:Tratamiento): Observable<any> {
    return this.http.put(this.URL_ROOT + `/update/${tratamiento.id}`,tratamiento);
  }

  deleteById(id:number): Observable<any> {
    return this.http.delete(this.URL_ROOT + `/delete/${id}`);
  }

// KPIs

  obtenerTop3TratamientosMasUnidadVendida(): Observable<Tratamiento[]> {
    return this.http.get<Tratamiento[]>(
      this.URL_ROOT + `/get-top3-tratamientos-mas-unidad-vendida`
    );
  }

  obtenerNumTratamientosUltimoMes(): Observable<number> {
    return this.http.get<number>(this.URL_ROOT + `/get-num-tratamientos-ultimo-mes`);
  }

  obtenerNumTratamientosTipoDroga(tipoDroga:string): Observable<number>{
    return this.http.get<number>(this.URL_ROOT + `/get-num-tratamientos-tipo-droga/${tipoDroga}`);
  }

  obtenerTratamientosVeterinario(cedula:string): Observable<Tratamiento[]>{
    return this.http.get<Tratamiento[]>(this.URL_ROOT + `/tratamientos-veterinario/${cedula}`);
  }

}
