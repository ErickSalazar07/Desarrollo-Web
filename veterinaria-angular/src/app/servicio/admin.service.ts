import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from '../modelo/admin';
import { Observable } from 'rxjs';
import { UserEntity } from '../modelo/UserEntity';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  administradores: Admin[] = [];

  URL_ROOT: string = "http://localhost:8090/admin";

  findByCorreo(correo: string): Observable<Admin> {
    return this.http.get<Admin>(`${this.URL_ROOT}/get-admin-correo/${correo}`);
  }

  findByUsername(username: string): Observable<Admin> {
    return this.http.get<Admin>(`${this.URL_ROOT}/get-admin-username/${username}`);
  }

  findById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`${this.URL_ROOT}/get-admin/${id}`);
  }

  findAll(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.URL_ROOT}/admins`);
  }

  addAdministrador(admin: Admin): Observable<void> {
    return this.http.post<void>(`${this.URL_ROOT}/add`, admin);
  }

  updateAdministrador(admin: Admin): Observable<void> {
    return this.http.put<void>(`${this.URL_ROOT}/update/${admin.id}`, admin);
  }

  deleteAdministrador(id: number): Observable<void> {
    return this.http.delete<void>(`${this.URL_ROOT}/delete/${id}`);
  }

  login(userEntity: UserEntity): Observable<String>{
    return this.http.post(this.URL_ROOT + `/login`, userEntity,
      {
      responseType: 'text'
    });
  }

  encontrarRol(user:UserEntity): Observable<String>{
    return this.http.post<String>(`${this.URL_ROOT}/encontrar-rol`, user);
  }
  administradorHome(): Observable<Admin> {
    return this.http.get<Admin>(this.URL_ROOT + `/details`);
  }
}

