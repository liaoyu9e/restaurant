import { Component, OnInit } from '@angular/core';
import { MainMenuService } from '../../service/main-menu.service';
import { Category } from '../../model/Category';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.css']
})
export class MainMenuComponent implements OnInit {
  private isCollapsedList: boolean[];
  private categoryList: Category[];

  constructor(private menuService: MainMenuService) { }

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
