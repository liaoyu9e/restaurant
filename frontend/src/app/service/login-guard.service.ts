import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class LoginGuardService implements CanActivate {
  private loggedIn: boolean = false;


  constructor(private loginService : LoginService) {

  }

  canActivate() {
     return true;
  }

}
