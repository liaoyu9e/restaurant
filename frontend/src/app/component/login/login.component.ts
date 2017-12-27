import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../service/login.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private credential = {'email':'', 'password':''};

  constructor(private loginService: LoginService, private userService: UserService, private router: Router) {
  }

  onLogin() {
    this.loginService.login(this.credential.email, this.credential.password).subscribe(
      res=>{
        let token = res.headers.get("Authorization");
        localStorage.setItem("iMoonWalkPro", token);
        let nav = this.router;
        location.reload();
        this.sleep(1000);
        nav.navigate(['/dashboard']);
      },
      error=>{
      }
    );
  }

  sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
  }

  ngOnInit() {
   
  }

}
