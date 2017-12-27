import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {CreditCard} from '../model/creditCard';

@Injectable()
export class CreditCardService {
	private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  addCreditCard(creditCard: CreditCard) {
    let url = this.serverPath+"/creditCard/";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.post(url, creditCard, {headers : tokenHeader});
  }

  getCreditCardList() {
  	let url = this.serverPath+"/creditCard/list";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  getCreditCardById(id:number) {
    let url = this.serverPath+"/creditCard/"+id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  updateCreditCard(creditCard: CreditCard) {
    let url = this.serverPath+"/creditCard/";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.put(url, creditCard, {headers : tokenHeader});
  }

  removeCreditCardById(id: number) {
    let url = this.serverPath+"/creditCard/" + id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.delete(url, {headers : tokenHeader});
  }
}
