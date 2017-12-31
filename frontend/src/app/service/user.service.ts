import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {User} from '../model/User';

@Injectable()
export class UserService {
  private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  newUser(user: User) {
    let url = this.serverPath+"/user/";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
    });
    return this.http.post(url, user, {headers : tokenHeader});
  }

  getUser(){
    let url = this.serverPath+"/user/";
    
    let tokenHeader = new Headers ({
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  updateUser(user: User) {

    let url = this.serverPath+"/user/";

    let userInfo = {
      "firstName" : user.firstName,
      "lastName" : user.lastName,
      "phone" : user.phone,
      "fax" : user.fax,
      "company" : user.company
    };
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });

    return this.http.put(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  updatePassword(currentPassword: string, newPassword: string) {
    let url = this.serverPath+"/user/password";

    let passwordInfo = {
      "currentPassword" : currentPassword,
      "newPassword" : newPassword,
    };
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });

    return this.http.put(url, JSON.stringify(passwordInfo), {headers : tokenHeader});
  }

  addGuest(guest : User) {
    let url = this.serverPath+"/guest/";

    let guestInfo = {
      "firstName" : guest.firstName,
      "lastName" : guest.lastName,
      "email" : guest.email
    };
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
    });
    return this.http.post(url, JSON.stringify(guestInfo), {headers : tokenHeader});
  }
  
}