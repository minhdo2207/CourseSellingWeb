import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';
import { QuestionService } from 'src/app/service/question.service';

@Component({
  selector: 'app-course-detail-lesson-detail-form',
  templateUrl: './course-detail-lesson-detail-form.component.html',
  styleUrls: ['./course-detail-lesson-detail-form.component.scss']
})
export class CourseDetailLessonDetailFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();

  onCloseModal() {
    this.question = '';
    this.answer1 = '';
    this.answer2 = '';
    this.answer3 = '';
    this.answer4 = '';
    this.correctAnswer = '';
    this.closeModal.emit();
  }
  
  userData: any;
  error: string = '';
  lessonId: any;

  constructor(
    private alertSrv: AlertService,
    private questionSrv: QuestionService,
    private route: ActivatedRoute
  ) {
    this.lessonId = this.route.snapshot.paramMap.get('id');
  }

  question: string = '';
  answer1: string = '';
  answer2: string = '';
  answer3: string = '';
  answer4: string = '';
  correctAnswer: string = '';

  onSubmit() {
    if (this.question == '' || this.answer1 == '' || this.answer2 == '' || this.answer3 == '' || this.answer4 == '' || this.correctAnswer == '') {
      this.alertSrv.showError('Thông tin nhập không hợp lệ', 'Lỗi!');
    }else{
      if(this.data.type == 'UPDATE'){
        this.questionSrv.edit(
          {question: this.question, choiceA: this.answer1, choiceB: this.answer2, choiceC: this.answer3, choiceD: this.answer4, solution: this.correctAnswer},
          this.data.record.questionId,
          (res: any) => {
            if(res){
              this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
              this.onCloseModal();
            }
          }
        )
      }else{
        this.questionSrv.create(
          {question: this.question, choiceA: this.answer1, choiceB: this.answer2, choiceC: this.answer3, choiceD: this.answer4, solution: this.correctAnswer},
          (res: any) => {
            if(res){
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
      this.question = this.data.record.question;
      this.answer1 = this.data.record.choiceA;
      this.answer2 = this.data.record.choiceB;
      this.answer3 = this.data.record.choiceC;
      this.answer4 = this.data.record.choiceD;
      this.correctAnswer = this.data.record.solution;
    }
  }

  ngOnInit() {
    
  }
}
