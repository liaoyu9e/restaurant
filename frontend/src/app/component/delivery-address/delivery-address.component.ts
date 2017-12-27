import { Component, OnInit } from '@angular/core';
import { Address } from '../../model/address';
import { AddressService } from '../../service/address.service';
import {Params, ActivatedRoute,Router} from "@angular/router";

@Component({
  selector: 'app-delivery-address',
  templateUrl: './delivery-address.component.html',
  styleUrls: ['./delivery-address.component.css']
})
export class DeliveryAddressComponent implements OnInit {
  private addressList: Address[];

  constructor(private addressService: AddressService, private router: Router, private route:ActivatedRoute) { }

  onRemoveAddress(address: Address) {
    this.addressService.removeAddressById(address.id).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      error => {
        console.log(error);
      }
    );
  }

  onEditAddress(address: Address) {
    this.router.navigate(['/dashboard/editAddress', address.id]);
  }

  ngOnInit() {
  	this.addressService.getAddressList().subscribe(
  		data => {
  			console.log(data.json());
        this.addressList = data.json();
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

}
