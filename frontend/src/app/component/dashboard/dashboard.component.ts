import { Component, OnInit, Input } from '@angular/core';
import { LandingComponent } from '../landing/landing.component';
import { SharedService } from '../../service/shared.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  private landingComponent: LandingComponent;

  constructor(private sharedService: SharedService) {
  }

  ngOnInit() {
  	this.sharedService.publishData("hideInfoPanel");
  }

}
