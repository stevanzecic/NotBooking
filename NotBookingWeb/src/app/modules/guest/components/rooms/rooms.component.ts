import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { GuestService } from '../../services/guest.service';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent {

  currPage = 1;
  rooms = [];
  totRooms: any;
  loading = false;

  constructor(private guestService: GuestService,
              private nzMessage: NzMessageService,
              private modalServ: NzModalService) { this.getRooms(); }

  getRooms() {
    this.guestService.getRooms(this.currPage - 1).subscribe(roomsResponse => {
      console.log(roomsResponse);
      this.rooms = roomsResponse.rooms;
      this.totRooms = roomsResponse.totPages * 1;
    });
  }

  pageIndexChange(pageIndex: any) {
    this.currPage = pageIndex;
    this.getRooms();
  }

  isVisibleMiddle = false;
  date: Date[] = [];
  checkIn: Date;
  checkOut: Date;
  iD: number;

  onChange(date: Date[]) {
    if (date.length === 2) {
      this.checkIn = date[0];
      this.checkOut = date[1];
    }
  }

  handleCancelMiddle() {
    this.isVisibleMiddle = false;
  }

  handleOkMiddle(): void {
    const obj = {
      userId: UserStorageService.getUserId(),
      roomId: this.iD,
      checkIn: this.checkIn,
      checkOut: this.checkOut
    };
    this.guestService.bookRoom(obj).subscribe(res => {
      this.nzMessage.success(`Booking request sent!`, { nzDuration: 5000 });
      this.isVisibleMiddle = false;
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
  }

  showModalMiddle(iD: number) {
    this.iD = iD;
    this.isVisibleMiddle = true;
  }

}
