import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';


@Injectable()
export class MainMenuService {

  private serverPath:string = "http://localhost:8080";
  
  constructor(private http:Http) { }

  getCategoryList() {
  	let url = this.serverPath+"/category/list";
    
    let tokenHeader = new Headers ({
      'Content-Type': 'application/json',
    });
    return this.http.get(url, {headers : tokenHeader});
  }

}
