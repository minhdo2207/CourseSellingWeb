import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../envirments/envirment.const';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  protected http: HttpClient;
  constructor(
    private injector: Injector,
    private alertSrv: AlertService
  ) {
    this.http = injector.get(HttpClient);
  }

  private token: string = String(localStorage.getItem('token'));

  headers = {
    Authorization: this.token
  };

  class(option: any, callBack: Function): any {
    let param = {};
    param = Object.assign({}, option);

    this.http.get(baseUrl + 'admin/enrollment', { observe: 'response', params: param, headers: this.headers }).subscribe(
      (response) => {
        if (response.body) {
          callBack(response.body);
        }
      },
      (error) => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Something went wrong', 'Lỗi!');
        }
      }
    )
  }

  course(option: any, callBack: Function): any{
    let param = {};
    param = Object.assign({size: 10}, option);

    this.http.get(baseUrl + 'admin/report/course', { observe: 'response', params: param, headers: this.headers }).subscribe(
      (response) => {
        if (response.body) {
          callBack(response.body);
        }
      },
      (error) => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Something went wrong', 'Lỗi!');
        }
      }
    )
  }

  student(option: any, callBack: Function): any{
    let param = {};
    param = Object.assign({}, option);

    this.http.get(baseUrl + 'admin/report/student', { observe: 'response', params: param, headers: this.headers }).subscribe(
      (response) => {
        if (response.body) {
          callBack(response.body);
        }
      },
      (error) => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Something went wrong', 'Lỗi!');
        }
      }
    )
  }
}
