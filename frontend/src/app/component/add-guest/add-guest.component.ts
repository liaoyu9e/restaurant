import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../model/User";
import {UserService} from "../../service/user.service";
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {

  private guest: User = new User();
  private emailcheck: string;
  private passwordcheck: string;
  private tempPassword: string;

  private userCreated: boolean = false;

  constructor(private userService: UserService, private router: Router, private loginService: LoginService) { }

  onAddGuest() {
    this.userService.addGuest(this.guest).subscribe(
      res => {
        console.log(res.json());
        this.guest = res.json();
        this.tempPassword = res.json().tempPassword;
        this.onContinueAsGuest();
      },
      error => {
        console.log(error.text());
      }
    );
  }

  onContinueAsGuest() {
  	this.loginService.login(this.guest.email, this.tempPassword).subscribe(
  	  res => {
  	  	let token = res.headers.get("Authorization");
        localStorage.setItem("iMoonWalkPro", token);
        let nav = this.router;
        location.reload();
        this.sleep(1000);
        nav.navigate(['/guest']);
  	  },
  	  error => {
  	  	console.log(error.text());
  	  }
  	)
  }

  ngOnInit() {
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
