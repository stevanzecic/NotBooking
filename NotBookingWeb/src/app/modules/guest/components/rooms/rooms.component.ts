import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { GuestService } from '../../services/guest.service';

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

}
