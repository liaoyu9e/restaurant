import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../service/login.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  private activeSession: boolean = false;

  constructor(private userService: UserService, 
    private loginService: LoginService, 
    private router: Router) { }

  logout(){
  	this.loginService.logout().subscribe(
      res => {
        localStorage.removeItem("iMoonWalkPro");
        this.activeSession=false;
        location.reload();
        let nav = this.router;
        nav.navigate(['/login']);
      },
      error => {
        console.log(error.text());
      }
    );

    
  }

  ngOnInit() {
  	if(localStorage.getItem("iMoonWalkPro")!=null) {
    	this.loginService.checkSession().subscribe(
        res => {
          this.activeSession=true;
        },
        error => {
          this.activeSession=false;
        }

      );
    }
  }

}
