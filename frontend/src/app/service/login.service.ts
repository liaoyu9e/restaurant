import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class LoginService {
  private serverPath:string = "http://localhost:8080";

  constructor (private http: Http) {}

  login(email: string, password: string) {
    let url = this.serverPath+"/login";
    
    let headers = new Headers(
      {
        'Content-Type': 'application/json',

        // 'Access-Control-Allow-Credentials' : true
      });

    let data = {"email" : email, "password" : password};

    return this.http.post(url, JSON.stringify(data), {headers: headers});
  }

  logout() {
    let url = this.serverPath+"/logout";
    
    let tokenHeader = new Headers ({
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });

    return this.http.post(url, "", {headers: tokenHeader});
  }

  // sendCredential(username: string, password: string) {
  //   let url = this.serverPath+"/token";
  //   let encodedCredentials = btoa(username+":"+password);
  //   let basicHeader = "Basic "+ encodedCredentials;
  //   let headers = new Headers(
  //     {
  //       'Authorization' : basicHeader

  //       // 'Access-Control-Allow-Credentials' : true
  //     });
  //   return this.http.get(url, {headers: headers});
  // }

  checkSession() {
    let url = this.serverPath+"/checkSession";
    let tokenHeader = new Headers ({
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }



}
