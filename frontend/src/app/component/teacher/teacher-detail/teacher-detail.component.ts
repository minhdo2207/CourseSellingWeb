import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-teacher-detail',
  templateUrl: './teacher-detail.component.html',
  styleUrls: ['./teacher-detail.component.scss']
})
export class TeacherDetailComponent {

  userId: any;
  userData: any;

  constructor(
    private userSrv: UserService,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ){
    this.userId = this.route.snapshot.paramMap.get('id');
    this.userSrv.getDetail(this.userId, (res: any) => {
      if(res){
        this.userData = res;
        this.userData.showTime = this.datePipe.transform(this.userData.userDob, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
      }
    })
  }

  ngOnInit() {
    
  }
}
