import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  message="";
  constructor(private userService : UserService, private router: Router){}
  ngOnInit() {}
  loginUser(){
    this.userService.loginUserRemote(this.user).subscribe(
      data => {
        console.log("response recieved");
        localStorage.setItem("logedUser", JSON.stringify(data) );
        this.router.navigate(['/dashboard']);
    },
      error => {console.log("exception occured");
      this.message="Invalid Credentials!";}
    );
  }
  gotoregister(){
    this.router.navigate(['/register']);
  }

}
