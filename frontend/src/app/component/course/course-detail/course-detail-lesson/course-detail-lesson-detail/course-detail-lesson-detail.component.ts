import { Component } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { DocumentService } from 'src/app/service/document.service';
import { LessonService } from 'src/app/service/lesson.service';
import { QuestionService } from 'src/app/service/question.service';

@Component({
  selector: 'app-course-detail-lesson-detail',
  templateUrl: './course-detail-lesson-detail.component.html',
  styleUrls: ['./course-detail-lesson-detail.component.scss']
})
export class CourseDetailLessonDetailComponent {

  typeId: any;
  lessonId: any;
  lessonData: any = '';

  constructor( 
    private alertSrv: AlertService,
    private lessonSrv: LessonService,
    private questionSrv: QuestionService,
    private route: ActivatedRoute,
    private documentService: DocumentService,

  ) {
    this.lessonId = this.route.snapshot.paramMap.get('id');
    this.route.params.subscribe(params => this.typeId = params['type']);
  }

  ngOnInit(){
    this.getAllData();
  }

  getAllData(){
    this.lessonSrv.getDetail(this.lessonId, (res: any) => {
      if(res){
        this.lessonData = res;
        this.questions = res.questions;
      }
    })

    this.documentService.getAll(this.lessonId, (res: any) => {
      this.documents = res.documents;
      console.log(this.documents);
    })
  }

  questions: any[] = [];

  documents = [
    {
      documentId: 1,
      documentLink: "google.com",
      documentTitle: "Tài liệu tìm hiểu sâu",
    }
  ]

  selectAll(event: any): void {
    const checked = event.target.checked;
    this.questions.forEach(student => student.selected = checked);
  }

  selectAll2(event: any): void {
    const checked = event.target.checked;
    this.questions.forEach(student => student.selected = checked);
  }

  isModalOpen = false;
  modalData: any;

  isModalOpen2 = false;
  modalData2: any;

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

  openModalDocument(record?: any) {
    if (record) {
      this.modalData = {
        record: record,
        title: 'Chỉnh sửa thông tin',
        type: 'UPDATE'
      };
    } else {
      this.modalData = {
        title: 'Thêm bài tài liệu mới',
        type: 'CREATE'
      };
    }

    this.isModalOpen = true;
  }


  openModalDocument2(record?: any) {
    if (record) {
      this.modalData2 = {
        record: record,
        title: 'Chỉnh sửa thông tin',
        type: 'UPDATE'
      };
    } else {
      this.modalData2 = {
        title: 'Thêm bài tài liệu mới',
        type: 'CREATE'
      };
    }

    this.isModalOpen2 = true;
  }


  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

  onCloseModal2() {
    this.isModalOpen2 = false;
    this.getAllData();
  }

  deleteRecord1(id: any) {
    this.questionSrv.deleteDetail(id,
      (res: any) => {
        if (res) {
          this.alertSrv.showSuccess('Xóa thành công dữ liệu', 'Thành công!');
          this.onCloseModal();
        }
      },
    )
  }

  deleteRecord2(id: any) {
    this.documentService.deleteDetail(id,
      (res: any) => {
        if (res) {
          this.alertSrv.showSuccess('Xóa thành công dữ liệu', 'Thành công!');
          this.onCloseModal();
        }
      },
    )
  }
  

}
