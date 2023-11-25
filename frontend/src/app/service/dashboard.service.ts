import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../envirments/envirment.const';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

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

  getUser(option: any, callBack: Function): any {
    let param = {};
    param = Object.assign({}, option);

    this.http.get(baseUrl + 'admin/user', { observe: 'response', params: param, headers: this.headers }).subscribe(
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

  getCourse(option: any, callBack: Function): any {
    let param = {};
    param = Object.assign({}, option);

    this.http.get(baseUrl + 'admin/course', { observe: 'response', params: param, headers: this.headers }).subscribe(
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
