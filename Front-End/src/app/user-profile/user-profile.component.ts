import { Component } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ImageProcessingService } from '../image-processing.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ProjectService } from '../project.service';
import { Project } from '../project';
import { map } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  userProjects: Project[];
  userloggedIn: User | null;

  constructor (private projectService: ProjectService,private imageProcessingService: ImageProcessingService,private router: Router
    ){}
   ngOnInit(): void {
 this.getUserProjects();
 this.getLogedUser();
   }
   getLogedUser(): User | null {
    const userJson = localStorage.getItem('logedUser');
    if (userJson) {
      return JSON.parse(userJson) as User;
    } else {
      return null;
    }
  }
  getUserProjects() {
    const user = this.getLogedUser();
    this.userloggedIn=user;
    if (user) {
       const user_id = user.id;
    this.projectService.getUserProjects(user_id)
      .pipe(
        map((x: Project[], i) => x.map((project: Project) => this.imageProcessingService.createImages(project))),
      )
      .subscribe(
        (resp: Project[]) => {
          this.userProjects = resp;
          console.log(this.userProjects);
          
        }, (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
  }}

  deleteProject(id: number) {
    this.projectService.destroy(id).subscribe(data =>{
      console.log(data);
      location.reload();
      
    })
  }
  



}
