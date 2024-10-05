import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';

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

  constructor(private adminService: AdminService,
              private nzMessage: NzMessageService,
              private modalServ: NzModalService) { this.getRooms(); }

  getRooms() {
    this.adminService.getRooms(this.currPage - 1).subscribe(roomsResponse => {
      console.log(roomsResponse);
      this.rooms = roomsResponse.rooms;
      this.totRooms = roomsResponse.totPages * 1;
    });
  }

  pageIndexChange(pageIndex: any) {
    this.currPage = pageIndex;
    this.getRooms();
  }

  deleteRoom(iD: number) {
    this.adminService.deleteRoom(iD).subscribe(res => {
      this.nzMessage.success('Room deleted successfully', { nzDuration: 5000 });
      this.getRooms();
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
  }

  delConfirm(iD: number) {
    this.modalServ.confirm({
      nzTitle: 'Room is about to be removed',
      nzContent: 'Are you sure you want to delete this room?',
      nzOkText: 'Delete',
      nzCancelText: 'Cancel',
      nzCentered: true,
      nzOnOk: () => {
        this.deleteRoom(iD);
      }
    });
  }
}
