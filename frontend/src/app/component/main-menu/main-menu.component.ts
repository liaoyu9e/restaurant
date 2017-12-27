import { Component, OnInit } from '@angular/core';
import { MainMenuService } from '../../service/main-menu.service';
import { Category } from '../../model/Category';
import { Food } from '../../model/Food';
import {Params, ActivatedRoute,Router} from "@angular/router";


@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  private isCollapsedList: boolean[];
  private categoryList: Category[];

  constructor(private menuService: MainMenuService, private router: Router, private route:ActivatedRoute) { }

  onViewFood(food: Food) {
    console.log("clicked");
    this.router.navigate(['/foodDetail', food.id]);
  }

  ngOnInit() {
  	this.menuService.getCategoryList().subscribe(
  		data => {
  			console.log(data.json());
  			this.categoryList=data.json();
  			let length = this.categoryList.length;
  			this.isCollapsedList = new Array<boolean>(length);;
  		},
  		error => {
  			console.log(error);
  		}
  	);
  }

}
