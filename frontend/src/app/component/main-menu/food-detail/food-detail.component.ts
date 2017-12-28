import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute,Router} from "@angular/router";
import { FoodService } from '../../../service/food.service';
import { Food } from '../../../model/Food';

@Component({
  selector: 'app-food-detail',
  templateUrl: './food-detail.component.html',
  styleUrls: ['./food-detail.component.css']
})
export class FoodDetailComponent implements OnInit {
  private food: Food = new Food();
  private qty:number = 1;

  constructor(
  	private foodService: FoodService,
  	private route: ActivatedRoute, 
    private router:Router) { 
  	
  }

  onAddToCart() {
    this.foodService.addFoodToCart(this.food.id, this.qty).subscribe(
      data => {
        console.log(data.json());
      },

      error => {
        console.log(error.text());
      }
    );
  }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      let id = Number.parseInt(params['id']);
      this.foodService.getFoodById(id).subscribe(
        data => {
          this.food=data.json();
        },
        error => {
          console.log(error.text());
        }
      );
    });
  }

}
