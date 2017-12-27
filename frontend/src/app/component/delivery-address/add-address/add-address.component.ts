import { Component, OnInit } from '@angular/core';
import { Address } from '../../../model/address';
import { AddressService } from '../../../service/address.service';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit {

  private address: Address = new Address();
  private addressAdded: boolean = false;

  constructor(private addressService : AddressService) { }

  onAddAddress() {
  	this.addressService.addAddress(this.address).subscribe(
  		data => {
  			console.log(data);
  			this.addressAdded=true;
  		}, 
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  ngOnInit() {
  }

}
