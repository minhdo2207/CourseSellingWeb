import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';
import { ClassService } from 'src/app/service/class.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-course-detail-chaper-form',
  templateUrl: './course-detail-chaper-form.component.html',
  styleUrls: ['./course-detail-chaper-form.component.scss']
})
export class CourseDetailChaperFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.closeModal.emit();
  }

  courseId: any;
  type: any;

  constructor(
    private alerSrv: AlertService,
    private chapterSrv: ChapterService,
    private route: ActivatedRoute,
    private classSrv: ClassService,
    private userSrv: UserService
  ){
    this.courseId = this.route.snapshot.paramMap.get('id');
  }

  onSubmit() {
    if(this.type == 1){
      if (this.chapterTitle == '' || this.chapterNo == ''){
        this.alerSrv.showError('Thông tin nhập chưa chính xác', 'Lỗi!');
      }else{
        this.chapterSrv.create(
          {chapterTitle: this.chapterTitle, chapterNp: this.chapterNo},
          (res: any) => {
            if(res){
              this.alerSrv.showSuccess('Thêm mới thành công', 'Thành công!');
              this.onCloseModal();
            }
          },
          this.courseId
        )
      }
    }else{
      this.classSrv.create(
        {className: this.chapterNo, classLink: this.chapterTitle, tutorId: this.tutorId},
        (res: any) => {
          if(res){
            this.alerSrv.showSuccess('Thêm mới lớp thành công', 'Thành công!');
            this.onCloseModal();
          }
        },
        this.courseId
      )
    }
  }

  ngOnChanges(){
    // console.log(this.data);
    this.type = this.data.type;
    if(this.type == 2){
      this.userSrv.getFreeTeacher((res: any) => {
        if(res){
          this.teachers = res.body;
        }
      })
    }
  }

  test(){
    console.log(this.teachers);
  }

  chapterTitle: string = '';
  chapterNo: string = '';
  tutorId: any;
  teachers: any[] = [];

}
