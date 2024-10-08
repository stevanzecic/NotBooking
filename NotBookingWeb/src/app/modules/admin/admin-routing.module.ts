import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { RoomsComponent } from './components/rooms/rooms.component';
import { AddRoomComponent } from './components/add-room/add-room.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { ReservationsComponent } from './components/reservations/reservations.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'rooms', component: RoomsComponent },
  { path: 'add-room', component: AddRoomComponent },
  { path: 'room/:id/edit', component: UpdateRoomComponent},
  { path: 'reservations', component: ReservationsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
