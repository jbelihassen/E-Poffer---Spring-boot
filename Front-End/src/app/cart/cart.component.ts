import { Component } from '@angular/core';
import { Project } from '../project';
import { HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs';
import { User } from '../user';
import { ProjectService } from '../project.service';
import { ImageProcessingService } from '../image-processing.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  userJoinedProjects: Project[];
  
  userloggedIn: User | null;
  
  id: number

  constructor (private route: ActivatedRoute,private projectService: ProjectService,private imageProcessingService: ImageProcessingService,private router: Router
    ,private userService : UserService){}
   ngOnInit(): void {
 this.getUserJoinedProjects();
 this.getLogedUser();
 this.id = this.route.snapshot.params['id'];
 this.userloggedIn = new User;
 this.userService.findUserById(this.id).subscribe( data => {
  this.userloggedIn = data;
  console.log(data);
})


   }
   getLogedUser(): User | null {
    const userJson = localStorage.getItem('logedUser');
    if (userJson) {
      return JSON.parse(userJson) as User;
    } else {
      return null;
    }
  }
  getUserJoinedProjects() {
    const user = this.getLogedUser();
    this.userloggedIn=user;
    if (user) {
       const user_id = user.id;
    this.projectService.getUserJoinedProjects(user_id)
      .pipe(
        map((x: Project[], i) => x.map((project: Project) => this.imageProcessingService.createImages(project))),
      )
      .subscribe(
        (resp: Project[]) => {
          this.userJoinedProjects = resp;
          console.log(this.userJoinedProjects);
          
        }, (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
  }}
  leaveProject(id: any){
    const user = this.getLogedUser();
    if (user) {
      const user_id = user.id;
      if (confirm('Are you sure you want to leave the Offer?')) {
        this.projectService.leaveProject(id, user_id).subscribe(
          (resp: Project) => {
            console.log(resp);
            alert('You have successfully left the project.'); // log the response
            location.reload(); // reload the page after the user clicks OK
          }, (error: HttpErrorResponse) => {
            console.log(error);
          }
        );
      }
    }
  }
  
}
