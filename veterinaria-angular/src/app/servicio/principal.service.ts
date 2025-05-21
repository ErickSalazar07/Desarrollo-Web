import { Injectable } from '@angular/core';
import { UserEntity } from '../modelo/UserEntity';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PrincipalService {

  constructor(private http:HttpClient ) { }

    login(userEntity: UserEntity): Observable<String>{
        return this.http.post(`/login`, userEntity,
          {
          responseType: 'text'
        });
      }
  }

