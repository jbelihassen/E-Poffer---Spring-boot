import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
//swiper

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AllProjectsComponent } from './all-projects/all-projects.component';
import { AllUsersComponent } from './all-users/all-users.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { LandingPageComponent } from './landing-page/landing-page.component';
import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { ShowProductImagesDialogComponent } from './show-product-images-dialog/show-product-images-dialog.component';
import { ShowProjectComponent } from './show-project/show-project.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { CartComponent } from './cart/cart.component';
import { CountdownModule } from 'ngx-countdown';
import { ReviewComponent } from './review/review.component';
import Swiper from 'swiper';




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    AllProjectsComponent,
    AllUsersComponent,
    DashboardComponent,
    AddProjectComponent,
    LandingPageComponent,
    ShowProductImagesDialogComponent,
    ShowProjectComponent,
    UserProfileComponent,
    UpdateProjectComponent,
    CartComponent,
    ReviewComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatDialogModule,
    MatIconModule,
    MatButtonToggleModule,
    MatFormFieldModule,
    CountdownModule,

  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
