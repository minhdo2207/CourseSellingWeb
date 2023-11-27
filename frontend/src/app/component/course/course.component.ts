import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent {
  constructor(
    private router: Router,
    private courseSrv: CourseService,
    private alertSrv: AlertService
    ) {
    
  }

  ngOnInit(){
    this.getAllData();
  }

  formatCurrency(value: number): string {
    return value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
  }

  getAllData(){
    let option = {sortDir: 'desc', page: this.page, type: this.type, name: this.keySearch};
    this.courseSrv.getAll(option, (res: any) => {
      this.students = res.elements;
      this.paging = res.paging;
      this.students.forEach(item => {
        if (item.courseType == 1) item.courseTypeShow = 'Khóa video';
        else item.courseTypeShow = 'Khóa Meeting';
      })
    })
  }
  
  students: any[] = [];
  type: string = '';
  name: string = '';
  keySearch: string = '';
  paging: any = {};
  page = 1;

  selectAll(event: any): void {
    const checked = event.target.checked;
    this.students.forEach(student => student.selected = checked);
  }

  isModalOpen = false;
  modalData: any;

  openModal(record?: any) {
    if (record) {
      this.modalData = {
        record: record,
        title: 'Chỉnh sửa thông tin',
        type: 'UPDATE'
      };
    } else {
      this.modalData = {
        title: 'Thêm khoá học mới',
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
    this.router.navigate(['/course/detail', id]);
  }

  refreshData(){
    this.getAllData();
    this.alertSrv.showSuccess('Tải lại danh sách thành công', 'Thành công!');
  }

  changeValueSelect(e: any){
    switch(e.target.value){
      case '1':{
        this.type = '1';
        break;
      }
      case '2':{
        this.type = '2';
        break;
      }
      case 'all':{
        this.type = '';
        break;
      }
    };
    this.page = 1;
    this.getAllData();
  }

  onSearch(){
    this.keySearch = this.name;
    this.getAllData();
  }

  nextPage(){
    if (this.paging.page == this.paging.totalPage){
      this.alertSrv.showError('Không thể mở page tiếp theo', 'Lỗi!')
    }else{
      this.page++;
      this.getAllData();
    }
  }

  previousPage(){
    if (this.paging.page == 1){
      this.alertSrv.showError('Không thể mở page trước đó', 'Lỗi!')
    }else{
      this.page--;
      this.getAllData();
    }
  }

  deleteRecord(id: any) {
    this.courseSrv.deleteDetail(id,
      (res: any) => {
        if (res) {
          this.alertSrv.showSuccess('Xóa thành công dữ liệu', 'Thành công!');
          this.onCloseModal();
        }
      },
    )
  }
}
