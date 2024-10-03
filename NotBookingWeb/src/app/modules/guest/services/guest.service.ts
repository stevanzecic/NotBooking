import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/auth/services/storage/user-storage.service';

const BASE_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class GuestService {

  constructor(private http: HttpClient) { }

  getRooms(pgNum: number): Observable<any> {
    return this.http.get(BASE_URL + `api/guest/rooms/${pgNum}`, {
      headers: this.createAuthorizationHeader()});
  }

  getMyRooms(pgNum: number): Observable<any> {
    const userId = UserStorageService.getUserId();
    return this.http.get(BASE_URL + `api/guest/bookings/${userId}/${pgNum}`, {
      headers: this.createAuthorizationHeader()});
  }

  bookRoom(bookingDTO: any): Observable<any> {
    return this.http.post(BASE_URL + `api/guest/book`, bookingDTO, {
      headers: this.createAuthorizationHeader()});
  }

  createAuthorizationHeader(){
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set('Authorization', 'Bearer ' + UserStorageService.getToken());
  }

}
