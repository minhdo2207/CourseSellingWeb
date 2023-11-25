import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-teacher-form',
  templateUrl: './teacher-form.component.html',
  styleUrls: ['./teacher-form.component.scss']
})
export class TeacherFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();
  
  userData: any;
  error: string = '';

  constructor(
    private userSrv: UserService,
    private alertSrv: AlertService
  ) {}

  name: string = '';
  address: string = '';
  phone: string = '';
  email: string = '';
  courseCategory: string = 'programming';

  categories: string[] = ['programming', 'design', 'business'];

  onSubmit() {
    if (this.name == '' || this.address == '' || this.phone == '' || this.email == ''){
      this.alertSrv.showError('Thông tin nhập chưa hợp lệ', 'Lỗi!');
    }else{
      if (this.data.type == 'UPDATE'){
        this.userSrv.edit(
          this.data.record.userId,
          {userName: this.name, userEmail: this.email, userPhone: this.phone, userAddress: this.address},
          (res: any) => {
            if (res){
              this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
              this.onCloseModal();
            }
          }
        )
      }else{
        this.userSrv.create(2,
          {userName: this.name, userEmail: this.email, userPhone: this.phone, userAddress: this.address},
          (res: any) => {
            if (res){
              this.alertSrv.showSuccess('Thêm mới thành công', 'Thành công!');
              this.onCloseModal();
            }
          }
        )
      }
    }
  }

  ngOnChanges() {
    
    if (this.data?.type == 'UPDATE') {
      this.name = this.data.record.userName;
      this.address = this.data.record.userAddress;
      this.phone = this.data.record.userPhone;
      this.email = this.data.record.userEmail;
    }
  }

  ngOnInit() {
;
  }

  onCloseModal() {
    this.name= '';
    this.address = '';
    this.phone= '';
    this.email = '';
    this.closeModal.emit();
  }
}
