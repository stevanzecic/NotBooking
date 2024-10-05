import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuestComponent } from './guest.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { BookingsViewComponent } from './components/bookings-view/bookings-view.component';

const routes: Routes = [
  { path: '', component: GuestComponent },
  { path: 'rooms', component: RoomsComponent },
  { path: 'reservations', component: BookingsViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GuestRoutingModule { }
