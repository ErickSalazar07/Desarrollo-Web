import { Injectable } from '@angular/core';
import { Mascota } from '../modelo/mascota';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MascotaService {

  constructor(private http: HttpClient) { }

  mascotas: Mascota[] = [];
  URL_ROOT:string = "http://localhost:8090/mascota";

  findAll(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(this.URL_ROOT + '/mascotas');
  }

  findById(id: number): Observable<Mascota> {
    return this.http.get<Mascota>(this.URL_ROOT + `/get-mascota/${id}`);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.URL_ROOT + `/delete/${id}`);
  }

  addMascota(mascota: Mascota): Observable<any> {
    return this.http.post(this.URL_ROOT + '/add', mascota);
  }

  findMascotasByClienteCedula(cedula:string): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(this.URL_ROOT + `/mascotas-cliente/${cedula}`);
  }

  updateMascota(mascota: Mascota): Observable<any> {
    return this.http.put(this.URL_ROOT + `/update/${mascota.id}`, mascota);
  }
}
