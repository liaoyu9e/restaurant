import { Component, OnInit } from '@angular/core';
import { User } from '../../model/User';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-edit-user-info',
  templateUrl: './edit-user-info.component.html',
  styleUrls: ['./edit-user-info.component.css']
})
export class EditUserInfoComponent implements OnInit {
  private user: User = new User();
  private userUpdated: boolean = false;

  constructor(private userService: UserService) { }

  onUpdateUser() {
  	this.userService.updateUser(this.user).subscribe(
  		data => {
  			this.userUpdated=true;
  		}, 
  		error => {
  			console.log(error.text());
  		}
  	);
  }

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
