import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Food} from '../model/Food';
import {OrderInfo} from '../model/OrderInfo';
import {FoodToCart} from '../model/FoodToCart';

@Injectable()
export class CustomerOrderService {

   private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  checkout(orderInfo: OrderInfo) {
    let url = this.serverPath+"/order/";

    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.post(url, JSON.stringify(orderInfo), {headers : tokenHeader});
  }

  getOrderList() {
    let url = this.serverPath+"/order/list";

    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }
}
