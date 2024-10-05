import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent {

  roomDetailsForm: FormGroup;
  iD = this.activatedRoute.snapshot.params['id'];
  formError: String;
  name: String;
  type: String;
  price: number | String;

  constructor(private fb: FormBuilder,
              private message: NzMessageService,
              private router: Router,
              private adminService: AdminService,
              private activatedRoute: ActivatedRoute) {
    this.roomDetailsForm = this.fb.group({
      name: ['', [Validators.required]],
      type: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
    });
    this.getRoomById();
  }

  submitForm() {
    this.adminService.updateRoom(this.iD, this.roomDetailsForm.value).subscribe(res => {
      this.message.success(`Room details updated successfully`, { nzDuration: 5000 });
      this.router.navigateByUrl('/admin/rooms');
    }, error => {
      this.message.error(`${error.error}`, { nzDuration: 5000 });
    })
  }

  getRoomById() {
    this.adminService.getRoomById(this.iD).subscribe(res=>{
      this.roomDetailsForm.patchValue(res);
    } , error => {
      this.message.error(`${error.error}`, { nzDuration: 5000 });
    });
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
