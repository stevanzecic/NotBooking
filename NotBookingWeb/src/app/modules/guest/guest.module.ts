import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GuestRoutingModule } from './guest-routing.module';
import { GuestComponent } from './guest.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { NgZorroAntdModule } from 'src/app/NgZorroAntdModule';
import { FormsModule } from '@angular/forms';
import { BookingsViewComponent } from './components/bookings-view/bookings-view.component';


@NgModule({
  declarations: [
    GuestComponent,
    RoomsComponent,
    BookingsViewComponent
  ],
  imports: [
    CommonModule,
    GuestRoutingModule,
    NgZorroAntdModule,
    FormsModule
  ]
})
export class GuestModule { }
