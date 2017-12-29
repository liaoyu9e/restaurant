import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { User } from '../../model/User';

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {

  private user: User = new User();

  constructor(private userService: UserService) { }

  ngOnInit() {
  	if(localStorage.getItem("iMoonWalkPro")!=null) {
  		this.userService.getUser().subscribe(
  			data => {
  				this.user = data.json();
  			},
  			error => {
  				console.log(error);
  			}
  		);
  	}
  }

}
