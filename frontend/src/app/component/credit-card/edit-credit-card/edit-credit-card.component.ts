import { Component, OnInit } from '@angular/core';
import { CreditCard } from '../../../model/CreditCard';
import { CreditCardService } from '../../../service/credit-card.service';
import {Params, ActivatedRoute,Router} from "@angular/router";

@Component({
  selector: 'app-edit-credit-card',
  templateUrl: './edit-credit-card.component.html',
  styleUrls: ['./edit-credit-card.component.css']
})
export class EditCreditCardComponent implements OnInit {

  private creditCard: CreditCard = new CreditCard();
  private creditCardUpdated: boolean = false;

  constructor(
  	private creditCardService : CreditCardService, 
  	private route: ActivatedRoute, 
    private router:Router) { 
  	
  }

  onUpdateCreditCard() {
  	this.creditCardService.updateCreditCard(this.creditCard).subscribe(
  		data => {
  			this.creditCardUpdated=true;
  		}, 
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
      let id = Number.parseInt(params['id']);
      this.creditCardService.getCreditCardById(id).subscribe(
      	data => {
      		this.creditCard=data.json();
      	},
      	error => {
      		console.log(error.text());
      	}
      );
    });
  }

}
