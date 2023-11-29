import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.scss']
})
export class StudentDetailComponent {
  transactionResponseVOS: any[] = [];
  modalData: any;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private datePipe: DatePipe,
    private alertSrv: AlertService
  ) {

  }

  danhSachKhoaHocDaTonTai: Array<any>

  userId: any;
  userAddress = '';
  userPhone = '';
  userEmail = '';
  userName = '';

  ngOnInit() {
    this.userId = this.route.snapshot.paramMap.get('id');

    this.getAllData();
  }

  getAllData() {
    this.userService.getDetail(this.userId, (res: any) => {
      // item.showTime = 
      this.userAddress = res.userAddress;
      this.userPhone = res.userPhone;
      this.userEmail = res.userEmail;
      this.userName = res.userName;
      this.danhSachKhoaHocDaTonTai = res.transactionResponseVOS.map((item:any) => item.courses) as Array<any>
      
      this.transactionResponseVOS = res.transactionResponseVOS.map((item: any) => {
        let i = item;

        i.transactionDateShow = this.datePipe.transform(i.transactionDate, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
        return i
      });
    })
  }

  convertStatus(status: any) {
    if (status == '0') return 'Chưa Thanh Toán ';
    if (status == '1') return 'Thành Công';
    return 'Thất Bại ';
  }

  changeStatus(e: any, transactionId: any){
    this.userService.changeStatus(transactionId, (res: any) => {
      if(res){
        this.alertSrv.showSuccess('Thay đổi trạng thái thành công', 'Thành công!');
        this.getAllData();
      }
    }, {status: Number(e.target.value)})
  }

  deleteTransaction(id: any){
    // this.userService.deleteTransaction(id, (res: any) => {
    //   if(res){

    //   }
    // })
  }

  formatCurrency(value: number): string {
    return value.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
  }

  isModalOpen = false;

  openModal() {
    this.modalData = {
        acountId: this.userId,
        title: 'Thêm học giao dịch mới',
        type: 'POST'
    }

    this.isModalOpen = true;
  }

  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

}
