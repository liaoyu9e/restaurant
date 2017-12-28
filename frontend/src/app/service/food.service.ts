import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Food} from '../model/Food';

@Injectable()
export class FoodService {

  private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  getFoodById(id:number) {
    let url = this.serverPath+"/food/detail/"+id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  addFoodToCart(id: number, qty: number) {
    let url = this.serverPath+"/cart/add";
    let info = {
      "foodId" : id,
      "qty" : qty
    }
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.post(url, JSON.stringify(info) ,{headers : tokenHeader});
  }

}
