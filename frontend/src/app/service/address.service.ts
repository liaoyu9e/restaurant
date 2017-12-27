import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Address} from '../model/address';

@Injectable()
export class AddressService {
  private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  addAddress(address: Address) {
    let url = this.serverPath+"/address/";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.post(url, address, {headers : tokenHeader});
  }

  getAddressList() {
  	let url = this.serverPath+"/address/list";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  getAddressById(id:number) {
    let url = this.serverPath+"/address/"+id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  updateAddress(address: Address) {
    let url = this.serverPath+"/address/";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.put(url, address, {headers : tokenHeader});
  }

  removeAddressById(id: number) {
    let url = this.serverPath+"/address/" + id;
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("iMoonWalkPro")
    });
    return this.http.delete(url, {headers : tokenHeader});
  }
}
