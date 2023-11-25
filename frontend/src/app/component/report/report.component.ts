import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/service/alert.service';
import { ReportService } from 'src/app/service/report.service';
import { DatePipe } from '@angular/common';
import { ExcelService } from 'src/app/service/excel.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent {
  students: any[] = [];
  courses: any[] = [];
  enrollments: any[] = [];
  paging: any = {};
  page = 1;
  name: string = '';
  keySearch: string = '';

  isModalOpen = false;
  modalData: any;
  fullData: Array<any>;

  option = 'option1';

  from_date: any;
  to_date: any;

  topic = [
    {id: 1, title: 'Báo cáo học viên'},
    {id: 2, title: 'Báo cáo doanh thu'},
    {id: 3, title: 'Báo cáo đăng ký khóa học'}
  ]
  topicId = 1;

  changeTopicId(e: any){
    this.from_date = ''
    this.to_date = '';
    this.topicId = Number(e.target.value);
    this.getAllData();
    this.getFullData();
  } 

  constructor(
    private alertSrv: AlertService,
    private reportSrv: ReportService,
    private router: Router,
    private datePipe: DatePipe,
    private excelService: ExcelService
  ){}

  cntStudent = 0;
  cntCourse = 0;
  cntRevenue = 0; 

  typeCourse = '';

  ngOnInit() {
    this.getFullData();
    this.getAllData();
    //handle abstract data
    this.reportSrv.student({}, (res: any) => {
      if(res){
        this.cntStudent = res.elements.length;
        res.elements.forEach((item: any) => {
          this.cntCourse += item.totalCourse;
          this.cntRevenue += item.totalPay;
        })
      }
    });
  }

  getAllData(){
    let option = {sortDir: 'desc', page: this.page, userName: this.keySearch, startDate: this.from_date, endDate: this.to_date, type: this.typeCourse};
    switch(this.topicId){
      case 1:{
        option = Object.assign({size: 10}, option);
        this.reportSrv.student(option, (res: any) => {
          if(res){
            this.students = res.elements;
            this.paging = res.paging;
          }
        });
        break;
      }
      case 2:{
        this.reportSrv.course(option, (res: any) => {
          if(res){
            this.courses = res.elements;
            this.paging = res.paging;
          }
        });
        break;
      }
      case 3:{
        option = Object.assign({size: 10}, option);
        this.reportSrv.class(option, (res: any) => {
          if(res){
            this.enrollments = res.elements;
            this.paging = res.paging;
            this.enrollments.forEach(item => {
              if(item.status == 1) item.showStatus = 'Đã Tham Gia';
              if(item.status == 2) item.showStatus = 'Đã Hủy';
              if(item.status == 0) item.showStatus = 'Đang Chờ Lớp';
              item.showTime = this.datePipe.transform(item.created, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
            })
          }
        });
        break;
      }
    }
  }

  async getFullData() {
    let option = {sortDir: 'desc'};
    switch(this.topicId){
      case 1:{
        await this.reportSrv.student(option, (res: any) => {
          if(res){
            this.fullData = res.elements;
          }
        });
        break;
      }
      case 2:{
        await this.reportSrv.course(option, (res: any) => {
          if(res){
            this.fullData = res.elements;
          }
        });
        break;
      }
      case 3:{
        await this.reportSrv.class(option, (res: any) => {
          if(res){
            this.fullData = res.elements;
            // this.paging = res.paging;
            this.fullData.forEach(item => {
              if(item.status == 1) item.showStatus = 'Đã Tham Gia';
              if(item.status == 2) item.showStatus = 'Đã Hủy';
              if(item.status == 0) item.showStatus = 'Đang Chờ Lớp';
              item.showTime = this.datePipe.transform(item.created, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
            })
          }
        });
        break;
      }
    }
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
        title: 'Thêm khoá học mới',
        type: 'CREATE'
      };
    }

    this.isModalOpen = true;
  }

  selectAll(event: any): void {
    const checked = event.target.checked;
    this.students.forEach(student => student.selected = checked);
  }

  onSearch(){
    if(!this.from_date) this.from_date = '';
    if(!this.to_date) this.to_date = '';
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

  onDetail(id: any) {
    this.router.navigate(['/student/detail/', id], { queryParams: { type: 1 } });
  }

  onCloseModal() {
    this.isModalOpen = false;
  }

  exportFile() {
    let dataFile: any;
    if (this.topicId == 1) dataFile = this.fullData

    if (this.topicId == 2) {
      dataFile = this.fullData.map(data => {
        let item = data
        item.courseType = data.courseType == 1 ? 'Khóa video' : 'Khóa meeting'
        return item
      })
    }

    if (this.topicId == 3) {
      dataFile = this.fullData.map(data => {
        let item = data

        if (data.status == 1) item.showStatus = 'Đã Tham Gia';
        if (data.status == 2) item.showStatus = 'Đã Hủy';
        if (data.status == 0) item.showStatus = 'Đang Chờ Lớp';

        item.showTime = this.datePipe.transform(item.created, 'dd/MM/yyyy', 'Asia/Ho_Chi_Minh');
        delete item.created;
        delete item.status;
        return item;
      })
    } 
    this.excelService.exportToExcel(dataFile, 'Báo cáo', 'Sheet');
  }

  changeTypeCourse(type: any){
    this.typeCourse = type;
    this.getAllData();
  }
}
