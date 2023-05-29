import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllUsersComponent } from './all-users/all-users.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AllProjectsComponent } from './all-projects/all-projects.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { ShowProjectComponent } from './show-project/show-project.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { CartComponent } from './cart/cart.component';
import { ReviewComponent } from './review/review.component';

const routes: Routes = [
  {path: '', component: LandingPageComponent} ,
  {path: 'projects', component: AllProjectsComponent} ,
  {path: 'users', component:AllUsersComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'login', component:LoginComponent},
  {path: 'dashboard', component:DashboardComponent},
  {path: 'projects/new', component:AddProjectComponent},
  {path: 'project/:id', component:ShowProjectComponent},
  {path: 'profile/:id', component:UserProfileComponent},
  {path: 'cart/:id', component:CartComponent},
  {path: 'update/:id', component:UpdateProjectComponent},
  {path: 'reviews/:id', component:ReviewComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
