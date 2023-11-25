import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent {
  students: any[] = [];
  paging: any = {};
  page = 1;
  name: string = '';
  keySearch: string = '';

  isModalOpen = false;
  modalData: any;

  constructor(
    private alertSrv: AlertService,
    private userSrv: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getAllData();
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
        title: 'Thêm học viên mới',
        type: 'CREATE'
      };
    }

    this.isModalOpen = true;
  }

  getAllData() {
    let option = { roleId: 1, sortDir: 'desc', page: this.page, userName: this.keySearch };
    this.userSrv.getAll(option, (res: any) => {
      this.students = res.elements;
      this.paging = res.paging;
    })
  }

  selectAll(event: any): void {
    const checked = event.target.checked;
    this.students.forEach(student => student.selected = checked);
  }

  refreshData() {
    this.getAllData();
    this.alertSrv.showSuccess('Tải lại danh sách thành công', 'Thành công!');
  }
  onSearch() {
    this.keySearch = this.name;
    this.getAllData();
  }
  nextPage() {
    if (this.paging.page == this.paging.totalPage) {
      this.alertSrv.showError('Không thể mở page tiếp theo', 'Lỗi!')
    } else {
      this.page++;
      this.getAllData();
    }
  }

  previousPage() {
    if (this.paging.page == 1) {
      this.alertSrv.showError('Không thể mở page trước đó', 'Lỗi!')
    } else {
      this.page--;
      this.getAllData();
    }
  }

  onDetail(id: any) {
    this.router.navigate(['/student/detail/', id]);
  }

  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

  deleteRecord(id: any) {
    this.userSrv.deleteDetail(id,
      (res: any) => {
        if (res) {
          this.alertSrv.showSuccess('Xóa thành công dữ liệu', 'Thành công!');
          this.onCloseModal();
        }
      },
    )
  }
}
