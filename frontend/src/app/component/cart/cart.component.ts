import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../service/shared.service';
import { CartService } from '../../service/cart.service';
import { FoodToCart } from '../../model/FoodToCart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  private foodToCartList: FoodToCart[];
  private subtotal:number = 0;
  private grandTotal: number = 0;
  private shipping: number = 5;

  constructor(private sharedService: SharedService, private cartService: CartService) { }

  ngOnInit() {
  	this.sharedService.publishData("hideInfoPanel");

  	this.cartService.getFoodToCartList().subscribe(
  		data => {
  			console.log(data.json());
  			this.foodToCartList = data.json();

  			for (let i of this.foodToCartList) {
  				this.subtotal=this.subtotal+i.subtotal;
  			}

  			this.grandTotal=this.subtotal+this.shipping;
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

}
