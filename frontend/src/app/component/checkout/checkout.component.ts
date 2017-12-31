import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../service/shared.service';
import { CartService } from '../../service/cart.service';
import { FoodToCart } from '../../model/FoodToCart';
import { Address } from '../../model/address';
import { CustomerOrderService } from '../../service/customer-order.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  private foodToCartList: FoodToCart[];
  private address: Address = new Address();
  private subtotal:number = 0;
  private grandTotal: number = 0;
  private shipping: number = 5;


  constructor(private sharedService: SharedService, private cartService: CartService, private orderService: CustomerOrderService) { }

  onCheckout() {
    this.orderService.checkout().subscribe(
      data => {
        console.log(data.json());
      },
      error => {
        console.log(error.text());
      }
    );
  }

  ngOnInit() {
  	this.sharedService.publishData("hideInfoPanel");

  	this.cartService.getFoodToCartList().subscribe(
  		data => {
  			console.log(data.json());
  			this.foodToCartList = data.json();

  			for (let i of this.foodToCartList) {
  				this.subtotal=Number((this.subtotal+i.subtotal).toFixed(2));
  			}

  			this.grandTotal=this.subtotal+this.shipping;
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

}
