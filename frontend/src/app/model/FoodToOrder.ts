import { CustomerOrder } from './CustomerOrder';
import { Food } from './Food';

export class FoodToOrder {
	public id: number;
	public food: Food;
	public qty:number;
	public subtotal:number;
	public order:CustomerOrder;
}