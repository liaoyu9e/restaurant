import { Component, OnInit } from '@angular/core';
import { CustomerOrder } from '../../model/CustomerOrder';
import { CustomerOrderService } from '../../service/customer-order.service';

@Component({
  selector: 'app-view-order',
  templateUrl: './view-order.component.html',
  styleUrls: ['./view-order.component.css']
})
export class ViewOrderComponent implements OnInit {

  private orderList: CustomerOrder[];

  constructor(private orderService: CustomerOrderService) { }

  ngOnInit() {
  	this.orderService.getOrderList().subscribe(
  		data => {
  			this.orderList = data.json();
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

}
