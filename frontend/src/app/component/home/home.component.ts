import { Component } from '@angular/core';
import { DashboardService } from 'src/app/service/dashboard.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  students = 0;
  teachers = 0;
  courses = 0;

  constructor(
    private dashboardSrv: DashboardService
  ){
    this.dashboardSrv.getUser({roleId: 1}, (res: any) => {
      if(res){
        this.students = res.elements.length;
      }
    });

    this.dashboardSrv.getUser({roleId: 2}, (res: any) => {
      if(res){
        this.teachers = res.elements.length;
      }
    });

    this.dashboardSrv.getCourse({}, (res: any) => {
      if(res){
        this.courses = res.elements.length;
      }
    });
  }

}
