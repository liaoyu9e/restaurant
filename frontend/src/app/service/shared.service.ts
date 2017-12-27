import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';

@Injectable()
export class SharedService {

  constructor() { }

   // Observable string sources
  private caseNumber = new Subject<string>();  

  // Observable string streams
  caseNumber$ = this.caseNumber.asObservable();

    // Service message commands
  publishData(data: string) {
    this.caseNumber.next(data);
  }

}
