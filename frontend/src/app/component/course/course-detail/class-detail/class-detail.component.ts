import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ClassService } from 'src/app/service/class.service';

@Component({
  selector: 'app-class-detail',
  templateUrl: './class-detail.component.html',
  styleUrls: ['./class-detail.component.scss']
})
export class ClassDetailComponent {
  classs: any;
  classId: any;
  waitList: any[] = [];

  isModalOpen = false;
  modalData: any;

  constructor(
    private classService: ClassService,
    private route: ActivatedRoute,
    private alertSrv: AlertService
  ) {
    this.classId = this.route.snapshot.paramMap.get('id');
    
  }

  cnt = 0;

  idShow = 1;
  typeShow = [
    {id: 1, title: 'Danh sách học sinh', list: 'Số học sinh trong lớp: '},
    {id: 2, title: 'Danh sách học sinh đang chờ', list: 'Số học sinh đang chờ: '}
  ]

  getAllData(){
    this.classService.getDetail(this.classId, (res: any) => {
      this.classs = res;
      this.cnt = this.classs.students.length;
    })
  }

  
  ngOnInit() { 
    this.getAllData();
  }

  changeTypeList(){
    if(this.idShow == 1){
      this.getAllData();
    }else{
      this.classService.getWaitList(this.classId, (res: any) => {
        if(res){
          this.waitList = res.body;
          this.cnt = this.waitList.length;
        }
      })
    }
  }

  deleteStudent(userId: any){
    this.classService.deleteStudent(
      {classId: this.classId, userId: userId},
      (res: any) => {
        if(res){
          this.alertSrv.showSuccess('Xóa thành công','Thành công!');
          this.changeTypeList();
        }
      }
    )
  }

  openModal() {
    this.isModalOpen = true;
  }

  onCloseModal() {
    this.isModalOpen = false;
    this.getAllData();
  }

}
