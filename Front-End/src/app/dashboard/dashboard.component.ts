import { Component } from '@angular/core';
import { Project } from '../project';
import { ProjectService } from '../project.service';
import { HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs';
import { ImageProcessingService } from '../image-processing.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

})
export class DashboardComponent {
  projects: Project[];
  productDetails: Project[];
  userloggedIn: User | null;
  title: string;
  
  
  constructor (private projectService: ProjectService,private imageProcessingService: ImageProcessingService,private router: Router
   ){}
  ngOnInit(): void {
this.getProjects();
this.getLogedUser();

  }
  searchByKeyword(searchkeyword :any) {
    console.log(searchkeyword);
     this.productDetails = [];
     this.getProjects(searchkeyword);
  }

  getProjects(searchKey: string = "") {
    // console.log("sqdfsd",searchKey);
    
    const user = this.getLogedUser();
    this.userloggedIn=user;
    this.projectService.getProjectsList(searchKey)
    .pipe(
      map((x: Project[], i) => x.map((project: Project) => this.imageProcessingService.createImages(project)))
    )
    .subscribe(
      (resp: Project[]) => {
      console.log(resp);
     this.productDetails = resp;
    }, (error: HttpErrorResponse) => {
      console.log(error);
    })
  }
  showProductDetails(id: any) {
    this.router.navigate([`/project/${id}`]);
  }
  getLogedUser(): User | null {
    const userJson = localStorage.getItem('logedUser');
    if (userJson) {
      return JSON.parse(userJson) as User;
    } else {
      return null;
    }
  }
  JoinProject(id: any){
    const user = this.getLogedUser();

    if (user) {
      const user_id = user.id;
      this.projectService.joinProject(id, user_id).subscribe(
        (resp: Project) => {
          console.log(resp);
          location.reload();
           
        }, (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
    }
  }
  isUserJoined(project: any): boolean {
    // Assuming you have a variable named 'loggedInUser' that stores the logged-in user's information
    return project.joinedUsers.includes(this.userloggedIn);
  }
  countDownDate =new Date();
  getRemainingTime(dueDate: Date ): string {

  
    const now = new Date();
    console.log(now);
    
    const timeDifference = dueDate.getDate() - now.getDate();
  
    if (timeDifference <= 0) {
      return 'Expired';
    }
  
    const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
    const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);
  
    return `${days} ${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
  }
  
  
  
  
  
  



}
