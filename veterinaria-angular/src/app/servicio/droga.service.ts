import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Droga } from '../modelo/droga';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DrogaService {

  constructor(private http:HttpClient) { }

  drogas:Droga[] = []
  URL_ROOT:string = "http://localhost:8090/droga";

  findAll(): Observable<Droga[]> {
    return this.http.get<Droga[]>(this.URL_ROOT + `/drogas`);
  }

  findById(id:number): Observable<Droga> {
    return this.http.get<Droga>(this.URL_ROOT + `/get-droga/${id}`);
  }

  addDroga(droga:Droga): Observable<any> {
    return this.http.post(this.URL_ROOT + `/add`,droga);
  }

  updateDroga(droga:Droga): Observable<any> {
    return this.http.put(this.URL_ROOT + `/update/${droga.id}`,droga);
  }

  deleteById(id:number): Observable<any> {
    return this.http.delete(this.URL_ROOT + `/delete/${id}`);
  }

// KPIs

  obtenerTotalGanancias(): Observable<number> {
    return this.http.get<number>(this.URL_ROOT + `/get-total-ganancias`);
  }

  obtenerTotalVentas(): Observable<number> {
    return this.http.get<number>(this.URL_ROOT + `/get-total-ventas`);
  }

}
