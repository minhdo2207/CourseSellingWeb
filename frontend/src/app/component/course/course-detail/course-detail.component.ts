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

  isShowFormChangeTime: boolean = false;

  // Lấy ngày hiện tại
  currentDate = new Date();
  timeStartCtrl = ''
  timeEndCtrl = ''


  type: number = 0;

  courseId: any;
  courseData: any;
  statusClass: any = ''
  classId: any = ''
  waitList: Array<any> = []
  ngOnInit() {
    this.classId = this.route.snapshot.paramMap.get('id');

    this.courseId = this.route.snapshot.paramMap.get('id');
    this.getAllData();
  }

  getAllData() {
    this.courseSrv.getDetail(this.courseId, (res: any) => {
      this.courseData = res;
      this.courseData.timeStart = this.datePipe.transform(this.courseData.courseStart, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
      this.courseData.timeEnd = this.datePipe.transform(this.courseData.courseEnd, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');

      // convert status
      let courseStartDate = new Date(this.courseData.courseStart); //  1703178000000
      let courseEndDate = new Date(this.courseData.courseEnd); // 1703782800000

      if (this.currentDate < courseStartDate) {
        this.statusClass = 'sắp khai giảng';
      } else if (this.currentDate >= courseStartDate && this.currentDate <= courseEndDate) {
        this.statusClass = 'đang diễn ra';
      } else {
        this.statusClass = 'đã kết thúc';
      }
    })


    this.classSrv.getWaitList(this.classId, (res: any) => {
      if (res) {
        this.waitList = res.body
        
      }
    })
  }


  isModalOpen = false;
  isModalOpen2 = false;
  modalData: any;

  openModal(title: string, record?: any) {
    if (record) {
      this.modalData = {
        record: record,
        type: this.courseData.courseType,
        title: title,
        typeForm: 'UPDATE'
      };
    } else {
      this.modalData = {
        title: title,
        type: this.courseData.courseType,
        typeForm: 'CREATE'
      };
    }

    this.isModalOpen = true;
  }


  openModal2() {
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

  onDetailClass(id: any) {
    console.log(id);

    this.router.navigate(['/course/detail/class/', id], {queryParams: {courseId: this.courseId}});
  }

  deleteChapter(chapterId: any) {
    this.chapterSrv.delete(chapterId, (res: any) => {
      if (res) {
        this.alertSrv.showSuccess('Xóa thành công', 'Thành công!');
        this.getAllData();
      }
    })
  }

  deleteClass(id: any) {
    this.classSrv.delete((res: any) => {
      if (res) {
        this.alertSrv.showSuccess('Xóa thành công', 'Thành công!');
        this.getAllData();
      }
    }, id)
  }

  convertStatus(numberStudent: any): string {
    if (30 > numberStudent) {
      return `Còn ${30 - numberStudent} chỗ`
    } else {
      return 'Đã hết chỗ'
    }
  }

  showListStudent(id: any) {
    this.router.navigate(['/course/detail/student', id]);
  }

  showFormChangeTime() {
    this.isShowFormChangeTime = true;
  }

  handleChangeTime() {
    let data = {
      courseStart: this.convertDateFormat(this.timeStartCtrl),
      courseEnd: this.convertDateFormat(this.timeEndCtrl)
    }

    this.courseSrv.editTime(data, this.courseId, (res: any) => {
      if (res) {
        this.alertSrv.showSuccess('Cập nhật thời gian thành công', 'Thành công!');
        this.getAllData();
      }
    })

    this.isShowFormChangeTime = false;
  }

  convertDateFormat(inputDate: any) {
    // Kiểm tra xem chuỗi đầu vào có tồn tại và có định dạng 'yyyy-mm-dd' không
    if (!inputDate || !/^\d{4}-\d{2}-\d{2}$/.test(inputDate)) {
      // Nếu không hợp lệ hoặc null, trả về chuỗi rỗng
      return '';
    }

    // Tách năm, tháng, ngày từ chuỗi đầu vào
    var parts = inputDate.split("-");

    // Tạo đối tượng ngày mới với định dạng mới
    var newDate = new Date(parts[0], parts[1] - 1, parts[2]);

    // Lấy ngày, tháng, năm từ đối tượng ngày mới
    var day = newDate.getDate();
    var month = newDate.getMonth() + 1; // Tháng bắt đầu từ 0
    var year = newDate.getFullYear();

    // Định dạng lại chuỗi theo 'dd-mm-yyyy'
    var formattedDate = day + "-" + month + "-" + year;

    return formattedDate;
  }
}