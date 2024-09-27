import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.scss']
})
export class AddRoomComponent {

  roomDetailsForm: FormGroup;
  formError: String;
  name: String;
  type: String;
  price: number | String;

  constructor(private fb: FormBuilder, private message: NzMessageService, private router: Router, private adminService: AdminService) {
    this.roomDetailsForm = this.fb.group({
      name: ['', [Validators.required]],
      type: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
    });
  }

  submitForm() {
    if (this.roomDetailsForm.invalid) {
      this.validateInput();
      return this.roomDetailsForm.errors;
    }
    this.adminService.addRoomDetails(this.roomDetailsForm.value).subscribe(response => {
      this.message.success('Room added successfully', { nzDuration: 5000 });
      this.router.navigateByUrl('/admin/dashboard');
  }, error => {
    this.message.error('${error.error}', { nzDuration: 5000 });
  });
    return '';
  }

  validateInput(): String {
    console.log(this.name + ' ' + this.type + ' ' + this.price);
    if (this.name === '' || this.name === undefined) {
      this.formError = 'Name cannot be empty';
    } else if (this.type === '' || this.type === undefined) {
      this.formError = 'Type cannot be empty';
    } else if (this.price === '' || this.price === undefined) {
      this.formError = 'Price cannot be empty';
    } else {
      this.formError = '';
    }
    if (this.formError !== '') {
      console.log(this.formError);
    }
    return this.formError;
  }

}
