import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../model/User";
import {UserService} from "../../service/user.service";
import {NgbDateStruct, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private user: User = new User();
  private emailcheck: string;
  private passwordcheck: string;

  private pickedDate: NgbDateStruct;
  private date: {year: number, month: number};
  private userCreated: boolean = false;


  constructor(private userService: UserService, private router: Router, private ngbDateParserFormatter: NgbDateParserFormatter) { }

  onCreateAccount() {
    this.user.birthday = this.ngbDateParserFormatter.format(this.pickedDate);
    this.userService.newUser(this.user).subscribe(
      res => {
        this.userCreated=true;
      },
      error => {
        console.log(error.text());
      }
    );
  }

  ngOnInit() {
  }

}
