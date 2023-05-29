import { Component } from '@angular/core';
import { Project } from '../project';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../project.service';
import { ImageProcessingService } from '../image-processing.service';
import { map } from 'rxjs/operators';
import { User } from '../user';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-show-project',
  templateUrl: './show-project.component.html',
  styleUrls: ['./show-project.component.css']
})
export class ShowProjectComponent {

  id: number
  project: Project
  constructor(
    private route: ActivatedRoute, private projectService: ProjectService, private imageProcessingService: ImageProcessingService) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.project = new Project();
    this.projectService.getProject(this.id).pipe(
      map(project => this.imageProcessingService.createImages(project))
    ).subscribe(data => {
      this.project = data;
      console.log(data);
      
    });


  }
  
  }


  // JoinProject(id: any){
  //   const user = this.getLogedUser();
  //   if (user) {
  //     const user_id = user.id;
  //     this.projectService.joinProject(id, user_id).subscribe(
  //       (resp: Project) => {
  //         console.log(resp); // log the response
  //       }, (error: HttpErrorResponse) => {
  //         console.log(error);
  //       }
  //     );
  //   }
  // }


