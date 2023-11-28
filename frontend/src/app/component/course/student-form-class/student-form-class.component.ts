import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ClassService } from 'src/app/service/class.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-student-form-class',
  templateUrl: './student-form-class.component.html',
  styleUrls: ['./student-form-class.component.scss']
})
export class StudentFormClassComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  students: any[] = [];

  userId: any;
  classId: any;

  constructor(
    private alertSrv: AlertService,
    private userSrv: UserService,
    private classSrv: ClassService,
    private route: ActivatedRoute
  ) {
    let option = { roleId: 1, sortDir: 'desc' };
    this.userSrv.getAllAll(option, (res: any) => {
      this.students = res.elements;
    });
    this.classId = this.route.snapshot.paramMap.get('id');
  }

  onSubmit() {
    this.classSrv.addStudent(
      this.classId,
      {userId: Number(this.userId)},
      (res: any) => {
        if(res){
          this.alertSrv.showSuccess('Thêm mới thành công', 'Thành công!');
          this.onCloseModal();
        }
      }
    )
  }

  onCloseModal() {
    this.closeModal.emit();
  }
}
