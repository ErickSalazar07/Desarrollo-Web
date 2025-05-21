import { Injectable } from '@angular/core';
import { UserEntity } from '../modelo/UserEntity';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {

  URL_ROOT = "http://localhost:8090";

  constructor(private http: HttpClient) { }

  login(userEntity: UserEntity): Observable<String> {
    return this.http.post(this.URL_ROOT + `/login`, userEntity,
      {
        responseType: 'text'
      });
  }

  obtenerUserEntityActivo(): Observable<UserEntity> {
    return this.http.get<UserEntity>(this.URL_ROOT + `/get-user-active`);
  }
}

