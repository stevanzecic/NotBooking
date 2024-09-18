import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private message: NzMessageService,
              private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      // username: [null, [Validators.email, Validators.required]],
      // Added patern for email validation so 'admin' can be used as username for login
      username: [null, [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$|admin')]],
      password: [null, [Validators.required]]
    });
  }

  submitForm() {
    this.authService.login(this.loginForm.value).subscribe(res => {
      console.log(res);
      if (res.userId != null) {
        const user = {
          id: res.userId,
          userRole: res.userRole
        };
        UserStorageService.saveUser(user);
        UserStorageService.saveToken(res.token);

        if (UserStorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/admin/dashboard");
        } else if (UserStorageService.isGuestLoggedIn()) {
          this.router.navigateByUrl("/guest/rooms");
        }
      }
    }, error => {
      this.message.error('Bad credentials', { nzDuration: 5000 });
    });
  }

}

