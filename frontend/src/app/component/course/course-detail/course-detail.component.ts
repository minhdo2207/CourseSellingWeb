import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.scss']
})
export class CourseDetailComponent {
  constructor(
    private router: Router,
    private courseSrv: CourseService,
    private route: ActivatedRoute,
    private chapterSrv: ChapterService,
    private alertSrv: AlertService
    ) {
    
  };
  type: number = 0;

  courseId: any;
  courseData: any;

  ngOnInit(){
    this.courseId = this.route.snapshot.paramMap.get('id');
    this.getAllData();
  }

  getAllData(){
    this.courseSrv.getDetail(this.courseId, (res: any) => {
      this.courseData = res;
      console.log(this.courseData);
    })
  }


  isModalOpen = false;
  modalData: any;

  openModal() {
    this.isModalOpen = true;
    this.modalData = { /* Your data here */ };
  }
  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

  onSetupLesson(id: any) {
    this.router.navigate(['/course/detail/setup-lesson', id], { queryParams: { type: this.courseData.courseType } });
  }

  deleteChapter(chapterId: any){
    this.chapterSrv.delete(chapterId, (res: any) => {
      if(res){
        this.alertSrv.showSuccess('Xóa thành công', 'Thành công!');
        this.getAllData();
      }
    })
  }
}
