import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { SharedService } from '../../service/shared.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  private showInfoPanel: boolean = true;
  private widerColumn: boolean = false;

  constructor(private sharedService: SharedService, private cdr: ChangeDetectorRef) { 
  	this.sharedService.caseNumber$.subscribe(
            data => {
                if(data=="hideInfoPanel") {
                	this.hideInfoPanel();
            	} else {
            		this.displayInfoPanel();
            	}
            });
  }

  hideInfoPanel() {
  	this.showInfoPanel=false;
  	this.widerColumn=true;
  }

  displayInfoPanel() {
  	this.showInfoPanel=true;
  	this.widerColumn=false;
  }

  ngOnInit() {
  }

  ngAfterViewChecked() {
    this.cdr.detectChanges();
  }


}
