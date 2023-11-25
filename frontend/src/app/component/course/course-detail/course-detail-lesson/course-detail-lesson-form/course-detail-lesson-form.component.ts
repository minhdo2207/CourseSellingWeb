import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';
import { LessonService } from 'src/app/service/lesson.service';

@Component({
  selector: 'app-course-detail-lesson-form',
  templateUrl: './course-detail-lesson-form.component.html',
  styleUrls: ['./course-detail-lesson-form.component.scss']
})
export class CourseDetailLessonFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.courseName = '';
    this.coursePrice = '';
    this.closeModal.emit();
  }
  
  userData: any;
  error: string = '';



  constructor(
    private lessonSrv: LessonService,
    private alertSrv: AlertService,
    private route: ActivatedRoute
  ) {}

  courseName: string = '';
  coursePrice: string = '';
  
  chapterId: any;

  onSubmit() {
    if (this.courseName == '' || this.coursePrice == ''){
      this.alertSrv.showError('Thông tin nhập không hợp lệ', 'Lỗi!');
      console.log(this,this.courseName, this.coursePrice)
    }else{
      if(this.data.type == 'UPDATE'){
        this.lessonSrv.edit(
          {lessonTitle: this.coursePrice, lessonVideoLink: this.courseName},
          this.data.record.lessonId,
          (res: any) => {
            if(res){
              this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
              this.onCloseModal();
            }
          }
        )
      }else{
        this.lessonSrv.create(
          {lessonTitle: this.coursePrice, lessonVideoLink: this.courseName},
          (res: any) => {
            if(res){
              this.alertSrv.showSuccess('Thêm mới thành công', 'Thành công!');
              this.onCloseModal();
            }
        }, this.chapterId)
      }
    }
  }

  ngOnChanges() {
    if (this.data?.type == 'UPDATE') {
      this.coursePrice = this.data.record.lessonTitle;
      this.courseName = this.data.record.lessonVideoLink;
    }
  }

  ngOnInit() {
    this.chapterId = this.route.snapshot.paramMap.get('id');
  }
}
