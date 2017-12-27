import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {
  private currentPassword:string;
  private newPassword:string;
  private newPasswordCheck:string;
  private passwordUpdated:boolean = false;

  constructor(private userService:UserService) { }

  onUpdatePassword(){
  	this.userService.updatePassword(this.currentPassword, this.newPassword).subscribe(
  		data => {
  			this.passwordUpdated=true;
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  ngOnInit() {
  }

}
