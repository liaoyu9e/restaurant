import { Component, OnInit, OnChanges, Input} from '@angular/core';
import { SharedService } from '../../service/shared.service';
import { CartService } from '../../service/cart.service';
import { FoodToCart } from '../../model/FoodToCart';
import { LoginService } from '../../service/login.service';
import {Params, ActivatedRoute,Router} from "@angular/router";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnChanges {
  private foodToCartList: FoodToCart[];
  private subtotal:number = 0;
  private grandTotal: number = 0;
  private shipping: number = 5;
  private tempQty: number;
  private loggedIn: boolean = false;

  constructor(private sharedService: SharedService, private cartService: CartService, private loginService: LoginService, private router: Router, private route:ActivatedRoute) { }
  @Input() major: number;



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

    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
      },
      error => {
        this.loggedIn = false;
        this.router.navigate(['/login']);
      }
    );
  }

  onFocus(foodToCart: FoodToCart) {
  	this.tempQty = foodToCart.qty;
  }

  onBlur(foodToCart: FoodToCart) {
  	if(this.tempQty != foodToCart.qty) {
	  	console.log(foodToCart);
	  	this.cartService.updateFoodQty(foodToCart).subscribe(
	  		data => {
	  			this.foodToCartList=data.json();
	  			location.reload();
	  		}, 
	  		error => {
	  			console.log(error.text());
	  		}
	  	);
  	}
  }

  onRemove(foodToCart: FoodToCart) {
    this.cartService.removeFood(foodToCart.id).subscribe(
      data => {
          this.foodToCartList=data.json();
          location.reload();
        }, 
        error => {
          console.log(error.text());
        }
    );
  }

  ngOnChanges() {

  }

}
