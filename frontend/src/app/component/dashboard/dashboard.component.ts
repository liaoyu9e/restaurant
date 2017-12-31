import { Component, OnInit, Input } from '@angular/core';
import { SharedService } from '../../service/shared.service';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  private guestAccount: boolean = false;


  constructor(private sharedService: SharedService, private userService: UserService) {
  }

  ngOnInit() {
  	this.sharedService.publishData("hideInfoPanel");

  	this.userService.getUser().subscribe(
  		res => {
  			console.log(res.json());
  			console.log(res.json().authorities[0].authority);
  			if(res.json().authorities[0].authority=="ROLE_GUEST") {
  				this.guestAccount = true;
  			} else {
  				this.guestAccount = false;
  			}
  		},
  		error => {
  			console.log(error.text());
  		}
  	)
  }

}
