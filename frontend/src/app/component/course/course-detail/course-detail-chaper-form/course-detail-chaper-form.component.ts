import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';

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

  constructor(
    private alerSrv: AlertService,
    private chapterSrv: ChapterService,
    private route: ActivatedRoute
  ){
    this.courseId = this.route.snapshot.paramMap.get('id');
  }

  onSubmit() {
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
  }

  chapterTitle: string = '';
  chapterNo: string = '';
}
