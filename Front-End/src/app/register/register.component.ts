import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = new User();
  message = "";
  error: any;

  constructor(private userService: UserService, private router: Router) {}
  registerUser() {
    this.userService.createUser(this.user).subscribe(
      data => {
        localStorage.setItem("logedUser", JSON.stringify(data) );
        this.router.navigate(['/dashboard']);       
      },
      error => {
        // Registration failed
        this.message = error.error;
        console.log(this.message);
      }
    );
  }

}
