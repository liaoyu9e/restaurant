import { Component, ViewEncapsulation } from '@angular/core';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.css'],
  providers: [NgbCarouselConfig],
  encapsulation: ViewEncapsulation.None
})
export class PromotionComponent{

  constructor(config: NgbCarouselConfig) {
    // customize default values of carousels used by this component tree
    config.interval = 5000;
    config.keyboard = false;
  }

}
