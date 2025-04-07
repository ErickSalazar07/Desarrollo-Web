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

  findAll(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>('http://localhost:8090/mascota/mascotas');
  }

  findById(id: number): Observable<Mascota> {
    return this.http.get<Mascota>('http://localhost:8090/mascota/get-mascota/' + id);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8090/mascota/delete/${id}`);
  }

  guardarMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.post<Mascota>('http://localhost:8090/mascota/add', mascota);
  }
  

  updateMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.put<Mascota>('http://localhost:8090/mascota/update/'+ mascota.id, mascota);
  }
}
