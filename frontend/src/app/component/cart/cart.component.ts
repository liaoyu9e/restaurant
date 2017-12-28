import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../service/shared.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private sharedService: SharedService) { }

  ngOnInit() {
  	this.sharedService.publishData("hideInfoPanel");
  }

}
