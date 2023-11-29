import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-transaction-form',
  templateUrl: './transaction-form.component.html',
  styleUrls: ['./transaction-form.component.scss']
})
export class TransactionFormComponent {
  @Input() data: any;
  @Input() danhSachKhoaHocDaTonTai: Array<any>;
  @Input() isOpen: boolean = true;
  @Output() closeModal = new EventEmitter<void>();
  userData: any;
  error: string = '';
  price = '';

  constructor(
    private alertSrv: AlertService,
    private courseService: CourseService,

  ) { }

  courseId: any = undefined;
  status: any;

  courseList: Array<any>

  onSubmit() {
    if (this.price == '') {
      // console.log(this.price);
      this.alertSrv.showError('Thông tin nhập chưa hợp lệ', 'Lỗi!');
    } else {
      let data = {
        "transactionDate": this.getCurrentDate(),
        "status": this.status,
        "transactionValue": this.price
      }

      console.log('data', data);
      console.log('courseId', this.courseId);
      console.log('acountId', this.data.acountId);

      this.courseService.crateTransaction(data, this.data.acountId, this.courseId, (res: any) => {
        this.alertSrv.showSuccess('Chỉnh sửa thành công', 'Thành công!');
        this.onCloseModal();
      })
    }
  }

  ngOnInit() {

  }

  ngOnChanges() {
    this.courseService.getAllCourses((res: any) => {
      this.courseList = res.elements.filter((element1: any) => !this.danhSachKhoaHocDaTonTai.some(element2 => element1.courseId === element2.courseId))
    })
    // console.log('this.data', this.data);

  }



  onCloseModal() {
    this.courseId = '';
    this.status = '';
    this.closeModal.emit();
  }

  getCurrentDate() {
    const currentDate = new Date();

    // Lấy thông tin về ngày, tháng, năm
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Thêm '0' đằng trước nếu tháng chỉ có một chữ số
    const day = currentDate.getDate().toString().padStart(2, '0'); // Thêm '0' đằng trước nếu ngày chỉ có một chữ số

    // Tạo chuỗi theo định dạng "yyyy-mm-dd"
    const formattedDate = `${year}-${month}-${day}`;

    return formattedDate;
  }
}
