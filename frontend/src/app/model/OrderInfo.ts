import { CustomerOrder } from './CustomerOrder';

export class OrderInfo {
  public id: number;
  public firstName: string;
  public lastName: string;
  public company: string;
  public street1: string;
  public street2: string;
  public apt: string;
  public city: string;
  public state: string;
  public shippingZipcode: string;
  public phone: string;
  public email: string;
  public cardNumber: string;
  public nameOnCard: string;
  public expiry: string;
  public cic: string;
  public billingZipcode: string;

  public customerOrder: CustomerOrder;
}