import { Component, OnInit } from '@angular/core';
import { Project } from '../project';
import { ProjectService } from '../project.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ShowProductImagesDialogComponent } from '../show-product-images-dialog/show-product-images-dialog.component';
import { ImageProcessingService } from '../image-processing.service';
// import { ImageProcessingService } from '../image-processing-service.service';

@Component({
  selector: 'app-all-projects',
  templateUrl: './all-projects.component.html',
  styleUrls: ['./all-projects.component.css']
 })
 export class AllProjectsComponent implements OnInit {
  
  projects: Project[];
   displayedColumns: string[] = [ 'Offer Title', 'Initial Price', 'Discounted Price', 'Offer Price', 'Actions'];
  imagesDialog: any;

  constructor (private projectService: ProjectService, public dialog: MatDialog, private imageProcessingService: ImageProcessingService){ this.imagesDialog = dialog;}
  ngOnInit(): void {
    this.getProjects();
  }
  getProjects() {
   this.projectService.getProjectsList()
   .pipe(
    map((x: Project[], i) => x.map((project: Project) => this.imageProcessingService.createImages(project)))
  )
   .subscribe(
    (resp:Project[]) =>{
      this.projects = resp;
      console.log(this.projects);
    },(error: HttpErrorResponse)=>{
      console.log(error);
    }
   )
  }
  deleteProject(id: number) {
    this.projectService.destroy(id).subscribe(data =>{
      console.log(data);
      this.getProjects();
    })
  }

  showImages(project: Project) {
  console.log(project);
  this.imagesDialog.open(ShowProductImagesDialogComponent, {
    data: {
      images: project.projectImages
    },
    height: '500px',
    width: '800px'
  });
}
}






















// export class AllProjectsComponent implements OnInit {
//   projects: Project[];
//   constructor (private projectService: ProjectService,
//     private imageProcessingService: ImageProcessingService){}
//   ngOnInit(): void {
// this.getProjects();
//   }
//   getProjects() {
//     this.projectService.getProjectsList()
//     .pipe(
//       map((x: Project[], i) => x.map((project: Project) => this.imageProcessingService.createImages(project)))
//     )
//     .subscribe(data =>{
//       this.projects = data;
//       console.log(this.projects);

//     })
//   }
// ;

  
// }
