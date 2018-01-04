import { Component, OnInit, NgZone, ChangeDetectorRef } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../service/login.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

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
        // this.sleep(1000);
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


  sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}



}
