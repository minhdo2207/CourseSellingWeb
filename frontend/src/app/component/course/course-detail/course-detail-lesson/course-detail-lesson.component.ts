import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';
import { LessonService } from 'src/app/service/lesson.service';

@Component({
  selector: 'app-course-detail-lesson',
  templateUrl: './course-detail-lesson.component.html',
  styleUrls: ['./course-detail-lesson.component.scss']
})
export class CourseDetailLessonComponent {
  isModalOpen = false;
  modalData: any;
  chapterId: any;
  chapterData: any;
  typeId: any;

  constructor(
    private lessonService: LessonService,
    private chapterService: ChapterService,
    private router: Router,
    private route: ActivatedRoute,
    private alertSrv: AlertService

  ){
    this.chapterId = this.route.snapshot.paramMap.get('id');
    this.route.queryParams.subscribe(params => this.typeId = params['type']);
    this.getAllData();
  }

  selectAll(event: any): void {
    const checked = event.target.checked;
    this.chapterData.lessonVos.forEach((item: any) => item.selected = checked);
  }


  getAllData(){
    this.chapterService.getDetail(this.chapterId, (res: any) => {
      if(res){
        this.chapterData = res;
      }
    })
  }

  ngOnInit() {
  
  }

  openModal(record?: any) {
    if (record) {
      this.modalData = {
        record: record,
        title: 'Chỉnh sửa thông tin',
        type: 'UPDATE'
      };
    } else {
      this.modalData = {
        title: 'Thêm bài học mới',
        type: 'CREATE'
      };
    }

    this.isModalOpen = true;
  }
  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  } 

  onDetail(id: any) {
    this.router.navigate(['/course/detail/setup-lesson/detail/', id], { queryParams: { type: this.typeId } });
  }

  deleteRecord(id: any) {
    this.lessonService.deleteDetail(id,
      (res: any) => {
        if (res) {
          this.alertSrv.showSuccess('Xóa thành công dữ liệu', 'Thành công!');
          this.onCloseModal();
        }
      },
    )
  }
}
