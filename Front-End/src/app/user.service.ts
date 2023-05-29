import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api'
  constructor(private httpClient: HttpClient) { }
  getUsersList(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseUrl}/users`);
  }
  deleteUser(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/users/${id}`);
  }

  createUser(user: User): Observable<any> {
    return this.httpClient.post<User>(`${this.baseUrl}/register`, user);
  }

  loginUserRemote(user: User): Observable<any> {
    return this.httpClient.post<User>(`${this.baseUrl}/login`, user);

  }
  findUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}/users/${id}`);

  }




}
