import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Food} from '../model/Food';
import {FoodToCart} from '../model/FoodToCart';

@Injectable()
export class CartService {

  private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  getFoodToCartList() {
    let url = this.serverPath+"/cart/foodList";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  updateFoodQty(foodToCart: FoodToCart){
  	let url = this.serverPath+"/cart/updateQty";

  	let foodToCartInfo = {
  		"id" : foodToCart.id,
  		"qty" : foodToCart.qty,
  		"subtotal" : foodToCart.subtotal
  	}
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.post(url, JSON.stringify(foodToCartInfo), {headers : tokenHeader});
  }

  removeFood(id: number) {
    let url = this.serverPath+"/cart/"+id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.delete(url, {headers : tokenHeader});
  }
}
