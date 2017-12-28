import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Routes, RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';
import { LandingComponent } from './component/landing/landing.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { PersonalInfoComponent } from './component/personal-info/personal-info.component';
import { EditUserInfoComponent } from './component/edit-user-info/edit-user-info.component';
import { DeliveryAddressComponent } from './component/delivery-address/delivery-address.component';
import { AddAddressComponent } from './component/delivery-address/add-address/add-address.component';
import { EditAddressComponent } from './component/delivery-address/edit-address/edit-address.component';
import { CreditCardComponent } from './component/credit-card/credit-card.component';
import { AddCreditCardComponent } from './component/credit-card/add-credit-card/add-credit-card.component';
import { EditCreditCardComponent } from './component/credit-card/edit-credit-card/edit-credit-card.component';
import { PasswordComponent } from './component/dashboard/password/password.component';
import { MainMenuComponent } from './component/main-menu/main-menu.component';
import { FoodDetailComponent } from './component/main-menu/food-detail/food-detail.component';
import { CartComponent } from './component/cart/cart.component';


import { UserService,  } from './service/user.service';
import { LoginService } from './service/login.service';
import { SharedService } from './service/shared.service';
import { AddressService } from './service/address.service';
import { CreditCardService } from './service/credit-card.service';
import { MainMenuService } from './service/main-menu.service';
import { FoodService } from './service/food.service';


const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent},
  { path: 'mainMenu', component: MainMenuComponent},
  { path: 'foodDetail/:id', component: FoodDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'cart', component: CartComponent },
  { path: 'dashboard', component: DashboardComponent,
    children: [
      {path: '',redirectTo: 'customerDetails', pathMatch: 'full'},
      {path: 'customerDetails',component: PersonalInfoComponent},
      {path: 'deliveryAddress',component: DeliveryAddressComponent},
      {path: 'editAddress/:id',component: EditAddressComponent},
      {path: 'editUserInfo',component: EditUserInfoComponent},
      {path: 'addAddress', component: AddAddressComponent},
      {path: 'creditCard',component: CreditCardComponent},
      {path: 'editCreditCard/:id',component: EditCreditCardComponent},
      {path: 'addCreditCard', component: AddCreditCardComponent},
      {path: 'password', component: PasswordComponent}
    ]
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    PersonalInfoComponent,
    HomeComponent,
    EditUserInfoComponent,
    DeliveryAddressComponent,
    AddAddressComponent,
    EditAddressComponent,
    CreditCardComponent,
    AddCreditCardComponent,
    EditCreditCardComponent,
    PasswordComponent,
    MainMenuComponent,
    FoodDetailComponent,
    CartComponent
  ],
  imports: [
  	NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } // <-- debugging purposes only
    )
  ],
  exports: [
    LandingComponent
  ],
  providers: [
    UserService,
    LoginService,
    SharedService,
    AddressService,
    CreditCardService,
    MainMenuService,
    FoodService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
