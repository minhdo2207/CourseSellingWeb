import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';
import { ClassService } from 'src/app/service/class.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-lesson-form',
  templateUrl: './lesson-form.component.html',
  styleUrls: ['./lesson-form.component.scss']
})
export class LessonFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.chapterTitle = '';
    this.chapterNo = '';
    this.tutorId = undefined;
    this.closeModal.emit();
  }

  courseId: any;

  constructor(
    private alerSrv: AlertService,
    private classSrv: ClassService,
    private route: ActivatedRoute,
    private userSrv: UserService
  ){
    this.courseId = this.route.snapshot.paramMap.get('id');
  }

  onSubmit() {
    if (this.chapterTitle == '' || this.chapterNo == '' || !this.tutorId){
      this.alerSrv.showError('Thông tin nhập chưa chính xác', 'Lỗi!');
    }else{
      this.classSrv.create(
        {className: this.chapterTitle, classLink: this.chapterNo, tutorId: this.tutorId},
        (res: any) => {
          if(res){
            this.alerSrv.showSuccess('Thêm mới thành công', 'Thành công!');
            this.onCloseModal();
          }
        },
        this.courseId
      )
    }
  }

  chapterTitle: string = '';
  chapterNo: string = '';
  tutorId: any;
}
