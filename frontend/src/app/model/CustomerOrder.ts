import { User } from './User';
import { FoodToOrder } from './FoodToOrder';
import { Address } from './address';

export class CustomerOrder {
	public id:number;
	public status: string;
	public total:number;
	public creationDate: string;
	public user: User;
	public foodToOrderList: FoodToOrder;
	public address: Address;
}