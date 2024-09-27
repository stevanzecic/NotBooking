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

  createAuthorizationHeader(){
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set('Authorization', 'Bearer ' + UserStorageService.getToken());
  }

}