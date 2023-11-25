import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.scss']
})
export class StudentDetailComponent {
  transactionResponseVOS = [
    { transactionId: 1, courses: '#1234', transactionDate: '11/11/2023', transactionValue: 'Khoá ngữ văn 1', status: '1000000.0'},
  ];
  
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
  ) {
    
  }
  
  userId: any;
  userAddress = '';
  userPhone = '';
  userEmail = '';
  userName = '';

ngOnInit(){
  this.userId = this.route.snapshot.paramMap.get('id');
    
    this.getAllData();
  }

  getAllData() {
    this.userService.getDetail(this.userId, (res: any) => {
        this.userAddress = res.userAddress;
        this.userPhone = res.userPhone;
        this.userEmail = res.userEmail;
        this.userName = res.userName;
        this.transactionResponseVOS = res.transactionResponseVOS;
      })
  }

  convertStatus(status: any) {
    if (status == '0') return 'Chưa Thanh Toán ';
    if (status == '1') return 'Thành Công';
    return 'Thất Bại ';
  }
}
