// student-modal.component.ts
import { Component, Input, Output, EventEmitter } from '@angular/core';
import {
  FormControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss'],
})
export class CourseFormComponent {
  @Input() data: any;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();


  
  userData: any;
  error: string = '';

  constructor(
    private courseService: CourseService,
    private alertSrv: AlertService
  ) {}

  name: string = '';
  price: number = 0;
  grade: number = 0;
  courseCategory = 1;

  categories = [
    {id: 1, name: 'Khóa video'},
    { id: 2, name: 'Khóa meeting'}
  ];

  onSubmit() {
    // Thực hiện các thao tác lưu trữ dữ liệu ở đây
    if (this.data.type == 'UPDATE'){
      this.courseService.edit(
        { courseTitle: this.name, coursePrice: this.price, courseGrade: this.grade},
        this.data.record.courseId,
        (res: any) => {
          if(res){
            this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
            this.onCloseModal();
          }
        }
      )
    }else {
      this.courseService.create(
        { courseTitle: this.name, coursePrice: this.price, courseType: this.courseCategory, courseGrade: this.grade},
        (res: any) => {
          if(res){
            this.alertSrv.showSuccess('Thêm mới thành công', 'Thành công!');
            this.onCloseModal();
          }
        }
      )
    }
  }

  ngOnChanges() {
    if (this.data?.type == 'UPDATE') {
      console.log(this.data);
      this.grade = this.data.record.courseGrade;
      this.name = this.data.record.courseTitle;
      this.price = this.data.record.coursePrice;
    }
  }

  ngOnInit() {
    
  }

  onCloseModal() {
    this.name= '';
    this.price = 0;
    this.grade = 0;
    this.courseCategory= 1;
    this.closeModal.emit();
  }
}
