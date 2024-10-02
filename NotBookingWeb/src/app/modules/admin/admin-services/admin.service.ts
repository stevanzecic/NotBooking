import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

const BASE_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addRoomDetails(roomDTO: any): Observable<any> {
    return this.http.post(BASE_URL + 'api/admin/room', roomDTO, {
      headers: this.createAuthorizationHeader()});
  }

  getRooms(pgNum: number): Observable<any> {
    return this.http.get(BASE_URL + `api/admin/rooms/${pgNum}`, {
      headers: this.createAuthorizationHeader()});
  }

  getRoomById(iD: number): Observable<any> {
    return this.http.get(BASE_URL + `api/admin/room/${iD}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  updateRoom(iD: number, roomDTO: any): Observable<any> {
    return this.http.put(BASE_URL + `api/admin/room/${iD}`, roomDTO, {
      headers: this.createAuthorizationHeader()
    })
  }

  deleteRoom(iD: number): Observable<any> {
    return this.http.delete(BASE_URL + `api/admin/room/${iD}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getReservations(pgNum: number): Observable<any> {
    return this.http.get(BASE_URL + `api/admin/reservations/${pgNum}`, {
      headers: this.createAuthorizationHeader()});
  }

  changeResStatus(resId: number, resStatus: string): Observable<any> {
    return this.http.get(BASE_URL + `api/admin/reservation/${resId}/${resStatus}`, {
      headers: this.createAuthorizationHeader()});
  }

  createAuthorizationHeader(){
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set('Authorization', 'Bearer ' + UserStorageService.getToken());
  }

}
