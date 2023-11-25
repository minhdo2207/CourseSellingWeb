import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';
import { DocumentService } from 'src/app/service/document.service';
import { QuestionService } from 'src/app/service/question.service';

@Component({
  selector: 'app-course-detail-lesson-detail-form-document',
  templateUrl: './course-detail-lesson-detail-form-document.component.html',
  styleUrls: ['./course-detail-lesson-detail-form-document.component.scss']
})
export class CourseDetailLessonDetailFormDocumentComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.documentTitle = '';
    this.documentLink = '';
    this.closeModal.emit();
  }

  userData: any;
  error: string = '';
  lessonId: any;

  constructor(
    private alertSrv: AlertService,
    private questionSrv: QuestionService,
    private route: ActivatedRoute,
    private documentService: DocumentService
  ) {
    this.lessonId = this.route.snapshot.paramMap.get('id');
  }

  documentTitle: string = '';
  documentLink: string = '';

  onSubmit() {
    if (this.documentTitle == '' || this.documentLink == '') {
      this.alertSrv.showError('Thông tin nhập không hợp lệ', 'Lỗi!');
    } else {
      if (this.data.type == 'UPDATE') {
        let document = { documentTitle: this.documentTitle, documentLink: this.documentLink, }
        this.documentService.edit(document,
          this.data.record.documentId,
          (res: any) => {
            if (res) {
              this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
              this.onCloseModal();
            }
          },
        )
      } else {
        let document = { documentTitle: this.documentTitle, documentLink: this.documentLink, }

        this.documentService.create(document,
          (res: any) => {
            if (res) {
              this.alertSrv.showSuccess('Thêm mới thành công', 'Thành công!');
              this.onCloseModal();
            }
          },
          this.lessonId
        )
      }
    }
  }

  ngOnChanges() {
    if (this.data?.type == 'UPDATE') {
      this.documentTitle = this.data.record.documentTitle;
      this.documentLink = this.data.record.documentLink;
    }
  }

  ngOnInit() {

  }
}
