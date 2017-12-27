import { Component, OnInit } from '@angular/core';
import { CreditCard } from '../../../model/CreditCard';
import { CreditCardService } from '../../../service/credit-card.service';

@Component({
  selector: 'app-add-credit-card',
  templateUrl: './add-credit-card.component.html',
  styleUrls: ['./add-credit-card.component.css']
})
export class AddCreditCardComponent implements OnInit {

  private creditCard: CreditCard = new CreditCard();
  private creditCardAdded: boolean = false;

  constructor(private creditCardService : CreditCardService) { }

  onAddCreditCard() {
  	this.creditCardService.addCreditCard(this.creditCard).subscribe(
  		data => {
  			console.log(data);
  			this.creditCardAdded=true;
  		}, 
  		error => {
  			console.log(error.text());
  		}
  	);
  }

  ngOnInit() {
  }
}
