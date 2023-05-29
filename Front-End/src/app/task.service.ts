import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from './task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8080/api'
  constructor(private httpClient : HttpClient) { }

  //Get All Posts
  getReviewsList(): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseUrl}/reviews`);
  }

  //Create Post
  // createReview(task: Task): Observable<Task>{
  //   return this.httpClient.post<Task>(`${this.baseUrl}/reviews`,Task);
  // }
  createReview(task: Task): Observable<Task>{
    const headers = {'Content-Type': 'application/json'}; // Set the correct content type
    
    return this.httpClient.post<Task>(`${this.baseUrl}/reviews`, task, { headers });
  }

 


}