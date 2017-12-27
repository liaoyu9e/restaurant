import { Component, OnInit } from '@angular/core';
import { Address } from '../../../model/address';
import { AddressService } from '../../../service/address.service';
import {Params, ActivatedRoute,Router} from "@angular/router";

@Component({
  selector: 'app-edit-address',
  templateUrl: './edit-address.component.html',
  styleUrls: ['./edit-address.component.css']
})
export class EditAddressComponent implements OnInit {
  private address: Address = new Address();
  private addressUpdated: boolean = false;

  constructor(
  	private addressService : AddressService, 
  	private route: ActivatedRoute, 
    private router:Router) { 
  	
  }

  onUpdateAddress() {
  	this.addressService.updateAddress(this.address).subscribe(
  		data => {
  			this.addressUpdated=true;
  		}, 
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
      let id = Number.parseInt(params['id']);
      this.addressService.getAddressById(id).subscribe(
      	data => {
      		this.address=data.json();
      	},
      	error => {
      		console.log(error.text());
      	}
      );
    });
  }

}
