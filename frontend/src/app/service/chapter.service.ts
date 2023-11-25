import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../envirments/envirment.const';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class ChapterService {

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

  delete(option: any, callBack: Function): any {
    this.http.delete(baseUrl + `admin/chapter/${option}`, { observe: 'response', headers: this.headers }).subscribe(
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

  create(data: any, callBack?: Function, option?: any): any {
    this.http.post(baseUrl + `admin/chapter/course/${option}`, data, { observe: 'response', headers: this.headers }).subscribe(
      response => {
        if (response.body) {
          let body: any = Object.assign({}, response.body);
          if (body) {
            if (callBack) {
              callBack(response);
            }
          }
        }
      },
      error => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Something went wrong', 'Lỗi!');
        }
      }
    )
  }

  getDetail(option: any, callBack: Function): any{
    this.http.get(baseUrl + `admin/chapter/${option}`, { observe: 'response', headers: this.headers }).subscribe(
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

  deleteDetail(id: any, callBack: Function): any {
    this.http.delete(baseUrl + `admin/chapter/${id}`, { observe: 'response', headers: this.headers}).subscribe(
      response => {
        if (response.body) {
          let body: any = Object.assign({}, response.body);
          if (body) {
            if (callBack) {
              callBack(response);
            }
          }
        }
      },
      error => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Something went wrong', 'Lỗi!');
        }
      }
    )
  }
}
