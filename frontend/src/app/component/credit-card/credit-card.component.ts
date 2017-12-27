import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute,Router} from "@angular/router";
import {CreditCard} from '../../model/CreditCard';
import {CreditCardService} from '../../service/credit-card.service';

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent implements OnInit {

  private creditCardList: CreditCard[];

  constructor(private creditCardService: CreditCardService, private router: Router, private route:ActivatedRoute) { }

  onRemoveCreditCard(creditCard: CreditCard) {
    this.creditCardService.removeCreditCardById(creditCard.id).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      error => {
        console.log(error);
      }
    );
  }

  onEditCreditCard(creditCard: CreditCard) {
    this.router.navigate(['/dashboard/editCreditCard', creditCard.id]);
  }

  ngOnInit() {
  	this.creditCardService.getCreditCardList().subscribe(
  		data => {
  			console.log(data.json());
        this.creditCardList = data.json();
  		},
  		error => {
  			console.log(error.text());
  		}
  	);
  }

}
