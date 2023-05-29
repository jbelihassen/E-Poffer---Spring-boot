import { Component, NgModule, OnInit } from '@angular/core';
import { Project } from '../project';
import { NgForm } from '@angular/forms';
import { ProjectService } from '../project.service';
import { FileHandel } from '../imagesFile';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from '../user';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule } from '@angular/material/grid-list';


@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
  project: Project = {
    id: 0,
    title: "",
    description: "",
    iPrice: 0,
    oPrice: 0,
    cOffer: 0,
    dueDate: new Date,
    categorie: "",
    projectImages: [],
    posted_user : null,
    joinedUsers: [] ,

  }
  userloggedIn: User | null;
  
constructor(private projectService: ProjectService , private sanitizer : DomSanitizer, private router: Router){}
ngOnInit(): void {
  this.getLogedUser();
   
}

getLogedUser(): User | null {
  const userJson = localStorage.getItem('logedUser');
  console.log(userJson);
  if (userJson) {
    return JSON.parse(userJson) as User;
  } else {
    return null;
  }
}

addProject(projectForm : NgForm){
 
  this.project.posted_user = this.getLogedUser();
  const projectFormData = this.prepareFormData(this.project)
  this.projectService.addProject(projectFormData).subscribe(
    (response : Project)=>{
      console.log(response);
      this.router.navigate(['/dashboard']);   
      projectForm.reset();

    },
    (error : Error)=>{
      console.log("=========================",error);
      this.router.navigate(['/dashboard']);  

    }
  )
}
prepareFormData(project: Project): FormData {
  const formData = new FormData();
  formData.append(
    'project', 
    new Blob([JSON.stringify(project)], {type: 'application/json'})

  );
  for(var i = 0; i < project.projectImages.length; i++) {
  formData.append(
    'imageFile',
    project.projectImages[i].file,
    project.projectImages[i].file.name);}
  return formData;
  }

onFileSelected(event: any){
//  console.log(event);
if(event.target.files){
  const file = event.target.files[0];
  const fileHandel : FileHandel ={
    file : file,
    url : this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(file))
  }
  this.project.projectImages.push(fileHandel);
}
}

removeImages(i: number) {
  this.project.projectImages.splice(i, 1);
}

fileDropped(fileHandel: FileHandel) {
  this.project.projectImages.push(fileHandel);
}

}
