import { Component } from '@angular/core';
import { UserStorageService } from './auth/services/storage/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'NotBookingWeb';

  isGuestLoggedIn: boolean = UserStorageService.isGuestLoggedIn();
  isAdminLoggedIn: boolean = UserStorageService.isAdminLoggedIn();

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isGuestLoggedIn = UserStorageService.isGuestLoggedIn();
        this.isAdminLoggedIn = UserStorageService.isAdminLoggedIn();
      }
    });
  }

  logout() {
    UserStorageService.signOut();
    this.router.navigateByUrl("/");
    setTimeout(() => {
      window.location.reload();
    }, 100);
  }

}
