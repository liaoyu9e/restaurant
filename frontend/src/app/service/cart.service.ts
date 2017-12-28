import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Food} from '../model/Food';

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
}
