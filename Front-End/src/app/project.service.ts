import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from './project';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8080/api'
  constructor(private httpClient : HttpClient) { }
  getProjectsList(searchKey : String = ""): Observable<Project[]>{
    console.log("aaa",searchKey);
    
    return this.httpClient.get<Project[]>("http://localhost:8080/api/projects?searchKey="+searchKey);
  }
  addProject(project: FormData): Observable<Project>{
    return this.httpClient.post<Project>(`${this.baseUrl}/projects/new`, project);
  }
  destroy(id : number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
  getProject(id : number): Observable<Project>{
    return this.httpClient.get<Project>(`${this.baseUrl}/project/${id}`);
  }
  joinProject(projectId: number, userId: number): Observable<Project> {
    return this.httpClient.post<Project>(`${this.baseUrl}/projects/join/${projectId}`, userId);
  }
  leaveProject(projectId: number, userId: number): Observable<Project> {
    return this.httpClient.post<Project>(`${this.baseUrl}/projects/leave/${projectId}`, userId);
  }
  getUserProjects(userId: number): Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseUrl}/user/projects/${userId}`); 
  }
  getUserJoinedProjects(userId: number): Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseUrl}/user/JoinedProjects/${userId}`); 
  }
  updateProject(id : number, project:Project): Observable<Project>{
    return this.httpClient.put<Project>(`${this.baseUrl}/project/${id}`,project);
  }
  searchProjectsByTitle(title: String): Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseUrl}/projects/search/${title}`); 
  }
  //Project Reviews
  projectReviews(id:number): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseUrl}/reviews/${id}`);
  }


}
