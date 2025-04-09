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

  addCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>('http://localhost:8090/cliente/add',cliente);
  }

  findAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('http://localhost:8090/cliente/clientes');
  }


  findById(id: number): Observable<Cliente> {
    const cliente = this.http.get<Cliente>('http://localhost:8090/cliente/get-cliente/' + id);
    return cliente;
  }

  updateCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>('http://localhost:8090/cliente/update/'+cliente.id,cliente);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8090/cliente/delete/${id}`);
  }
}
