import { Component } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  currPage = 1;
  rooms = [];
  totRooms: any;
  loading = false;

  constructor(private adminService: AdminService, private nzMessage: NzMessageService) { this.getRooms(); }

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

}
