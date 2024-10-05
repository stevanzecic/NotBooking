import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { GuestService } from '../../services/guest.service';
import { NzModalService } from 'ng-zorro-antd/modal';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

@Component({
  selector: 'app-bookings-view',
  templateUrl: './bookings-view.component.html',
  styleUrls: ['./bookings-view.component.scss']
})
export class BookingsViewComponent {

  currPg: any = 1;
  totalPgs: any;
  bookingList: any;
  isVisibleMiddle = false;
  date: Date[] = [];
  checkIn: Date;
  checkOut: Date;
  iD: number;
  roomId: number;

  constructor(private guestService: GuestService,
              private nzMessage: NzMessageService,
              private modalServ: NzModalService) { this.getBookings(); }

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

  onChange(date: Date[]) {
    if (date.length === 2) {
      this.checkIn = date[0];
      this.checkOut = date[1];
    }
  }

  handleCancelMiddle() {
    this.isVisibleMiddle = false;
  }

  showModalMiddle(iD: number, roomId: number) {
    this.iD = iD;
    this.roomId = roomId;
    this.isVisibleMiddle = true;
  }


  handleOkMiddle(): void {
    const obj = {
      userId: UserStorageService.getUserId(),
      roomId: this.roomId,
      checkIn: this.checkIn,
      checkOut: this.checkOut
    };
    this.guestService.deleteBooking(this.iD).subscribe(res => {
      this.nzMessage.success(`Old booking removed!`, { nzDuration: 5000 });
      this.isVisibleMiddle = false;
      this.getBookings();
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
    this.guestService.bookRoom(obj).subscribe(res => {
      this.nzMessage.success(`Booking change request sent!`, { nzDuration: 5000 });
      this.isVisibleMiddle = false;
      this.getBookings();
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
  }



  deleteBooking(iD: number) {
    this.guestService.deleteBooking(iD).subscribe(res => {
      this.nzMessage.success('Booking deleted successfully', { nzDuration: 5000 });
      this.getBookings();
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
  }

  delConfirm(iD: number) {
    this.modalServ.confirm({
      nzTitle: 'Reservation is about to be removed',
      nzContent: 'Are you sure you want to delete this reservation?',
      nzOkText: 'Delete',
      nzCancelText: 'Cancel',
      nzCentered: true,
      nzOnOk: () => {
        this.deleteBooking(iD);
      }
    });
  }

}
