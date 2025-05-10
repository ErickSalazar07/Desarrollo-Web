import { Injectable } from '@angular/core';
import { Cliente } from '../modelo/cliente';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  clientes: Cliente[] = [];
  URL_ROOT:string = "http://localhost:8090/cliente";

  addCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.URL_ROOT + `/add`,cliente);
  }

  findAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.URL_ROOT + `/clientes`);
  }

  findById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(this.URL_ROOT + `/get-cliente/${id}`);
  }

  findByCedula(cedula:string): Observable<Cliente> {
    return this.http.get<Cliente>(this.URL_ROOT + `/get-cliente-cedula/${cedula}`);
  }

  updateCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(this.URL_ROOT + `/update/${cliente.id}`,cliente);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.URL_ROOT + `/delete/${id}`);
  }
}
