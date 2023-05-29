import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit{
  users: User[];
  constructor( private userService : UserService, private router: Router){}
  ngOnInit(): void {
    this.getUsers();
      
  }
  getUsers() {
    this.userService.getUsersList().subscribe(data =>
      this.users = data)
  }

  onDeleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(
      () => {
        console.log(`User with ID ${id} deleted successfully`);
        this.getUsers();   
      },
      (error) => {
        console.error(`Error deleting user with ID ${id}: ${error.message}`);
      }
    );
  }


}
