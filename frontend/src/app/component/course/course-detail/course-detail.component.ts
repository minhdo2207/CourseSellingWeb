import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ChapterService } from 'src/app/service/chapter.service';
import { ClassService } from 'src/app/service/class.service';
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
    private alertSrv: AlertService,
    private datePipe: DatePipe,
    private classSrv: ClassService
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
      this.courseData.timeStart = this.datePipe.transform(this.courseData.courseStart.created, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
      this.courseData.timeEnd = this.datePipe.transform(this.courseData.courseEnd.created, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
      // console.log(this.courseData);
    })
  }


  isModalOpen = false;
  isModalOpen2 = false;
  modalData: any;

  openModal() {
    this.isModalOpen = true;
    this.modalData = { type: this.courseData.courseType };
  }

  openModal2(){
    this.isModalOpen2 = true;
    this.modalData = { /* Your data here */ };
  }
  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

  onCloseModal2() {
    this.isModalOpen2 = false;
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

  deleteClass(id: any){
    this.classSrv.delete((res: any) => {
      if(res){
        this.alertSrv.showSuccess('Xóa thành công', 'Thành công!');
        this.getAllData();
      }
    }, id)
  }
}
