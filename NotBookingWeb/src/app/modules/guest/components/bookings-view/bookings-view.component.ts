import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { GuestService } from '../../services/guest.service';

@Component({
  selector: 'app-bookings-view',
  templateUrl: './bookings-view.component.html',
  styleUrls: ['./bookings-view.component.scss']
})
export class BookingsViewComponent {

  currPg: any = 1;
  totalPgs: any;
  bookingList: any;

  constructor(private guestService: GuestService, private nzMessage: NzMessageService) { this.getBookings(); }

  getBookings() {
    this.guestService.getMyRooms(this.currPg - 1 ).subscribe(res => {
      console.log(res);
      this.bookingList = res.resDTOList;
      this.totalPgs = res.totPgs * 5;
    }, error => {
      this.nzMessage.error(`$error.error`, { nzDuration: 5000 });
    });
  }

  pgIndexChange(pgIndex: number) {
    this.currPg = pgIndex;
    this.getBookings();
  }

}
