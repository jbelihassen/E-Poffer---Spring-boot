import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../project';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent {
  project : Project =new Project
  id : number;
  constructor (private projectService : ProjectService,
  private route :ActivatedRoute,
  private router : Router){}

ngOnInit(): void {
this.id = this.route.snapshot.params['id'];
this.projectService.getProject(this.id).subscribe(data=>{
this.project = data;
},error => console.log(error));
}


onSubmit(){
this.projectService.updateProject(this.id, this.project).subscribe(data=>{
 console.log(data);
this.router.navigate(['/dashboard']);
}),(error : Error)=>{
console.log("=========================",error);
this.router.navigate(['/dashboard']);

}

}
}
