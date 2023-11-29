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
  courseId: any;

  constructor(
    private alertSrv: AlertService,
    private userSrv: UserService,
    private classSrv: ClassService,
    private route: ActivatedRoute
  ) {
    this.route.queryParams.subscribe(param => {
      this.courseId = param['courseId'];
    })
    this.classId = this.route.snapshot.paramMap.get('id');
    this.classSrv.getWaitList(this.courseId, (res: any) => {
      this.students = res.body;
      console.log(this.students);
    });
  }

  ngOnInit(){
    
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
