import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private message: NzMessageService,
              private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]],
      name: [null, [Validators.required]]
    },
    { validator: CustomValidators.MatchingPasswords }
    );
  }

  submitForm() {
    if (this.registerForm.invalid) {
      return this.registerForm.errors;
    }
    this.authService.register(this.registerForm.value).subscribe(res => {
      if (res.id != null) {
        this.message.success("Registration successful", { nzDuration: 5000 });
        this.router.navigateByUrl("/");
      } else {
        this.message.error(`${res.message}`, { nzDuration: 5000 });
      }
    });
    return '';
  }

}


export class CustomValidators {
  static MatchingPasswords(control: AbstractControl) {
    const password = control.get('password')!.value;
    const confirmPassword = control.get('confirmPassword')!.value;
    const currentErrors = control.get('confirmPassword')!.errors;
    const confirmControl = control.get('confirmPassword');

    if (compare(password, confirmPassword)) {
      confirmControl?.setErrors({ ...currentErrors, not_matching: true });
      console.log('Detected unmatching passwords!');
    } else {
      confirmControl?.setErrors(currentErrors);
      console.log('Passwords match.');
    }
  }
}

function compare(password: string, confirmPassword: string) {
  return password !== confirmPassword && confirmPassword !== '';
}