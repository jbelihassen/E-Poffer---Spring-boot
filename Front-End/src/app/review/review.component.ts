import { Component , OnInit} from '@angular/core';
import { Task } from '../task';
import { Project } from '../project';
import { User } from '../user';
import { UserService } from '../user.service';
import { ProjectService } from '../project.service';
import { TaskService } from '../task.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ImageProcessingService } from '../image-processing.service';
import { map } from 'rxjs';
import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit  {
  ngOnInit(): void {
    this.getLogedUser();
    this.id = this.route.snapshot.params['id'];
    this.project= new Project();
    

    this.projectService.projectReviews(this.id).subscribe(data => {
      this.tasks = data},
      (error)=> {
        console.log(error);
      });
      this.projectService.getProject(this.id).pipe(
        map((project) => this.imageProcessingService.createImages(project))
      ).subscribe( data => {
        this.project = data;
        console.log(data);
      });
    
  }
  id:number;
  task: Task = {
    id: 0,
    title: '',
    rate:0,
    postedBy: null,
    projectReview: null,
  };

  userloggedIn: User | null;
  project: Project;
  tasks: Task[];
  rating: Number;
  constructor(
    private projectService: ProjectService,
    private userService: UserService,
    private router: Router,
    private taskService: TaskService,
    private route: ActivatedRoute,
    private imageProcessingService: ImageProcessingService
  ) {}

  getLogedUser(): User | null {
    const userJson = localStorage.getItem('logedUser');
    if (userJson) {
      return JSON.parse(userJson) as User;
    } else {
      return null;
    }
  }
  setRating(stars: number) {
    this.task.rate = stars;
  }

  createReview(taskForm: NgForm){
    this.task.postedBy=this.getLogedUser();
    console.log(this.task.postedBy);
    
    this.task.projectReview= this.project;
    console.log(this.task);
    
    this.taskService.createReview(this.task).subscribe((response : Task) => {
      this.projectService.projectReviews(this.id).subscribe((data)=>{
        this.tasks = data;
        taskForm.reset();
      },
      (error)=>{
        console.log(error);
      });
      this.router.navigate([`reviews/${this.project.id}`]);
  },
  (error)=>{
    console.log(error);
  }
  );
}
getStars(rate: number): number[] {
  return Array(rate).fill(0);
}
}
