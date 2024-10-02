import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent {

  currPage: any = 1;
  totRes: any;
  reservations: any;

  constructor(private adminService: AdminService, private nzMessage: NzMessageService) { this.getReservations() }

  getReservations() {
    this.adminService.getReservations(this.currPage - 1).subscribe(resResponse => {
      console.log(resResponse);
      this.reservations = resResponse.resDTOList;
      this.totRes = resResponse.totPgs * 5;
    });
  }

  pgIndexChange(pageIndex: any) {
    this.currPage = pageIndex;
    this.getReservations();
  }

  changeReservationStatus(iD: number, resStatus: string) {
    this.adminService.changeResStatus(iD, resStatus).subscribe(res => {
      this.nzMessage.success(`Reservation status changed!`, { nzDuration: 5000 });
      this.getReservations();
    }, error => {
      this.nzMessage.error(`${error.error}`, { nzDuration: 5000 });
    });
  }

}
