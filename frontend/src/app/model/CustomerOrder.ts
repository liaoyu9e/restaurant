import { User } from './User';
import { FoodToOrder } from './FoodToOrder';

export class CustomerOrder {
	public id:number;
	public status: string;
	public total:number;
	public creationDate: string;
	public user: User;
	public foodToOrderList: FoodToOrder;
}