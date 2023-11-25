import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { AlertService } from './alert.service';
import { baseUrl } from '../envirments/envirment.const';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

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

  create(data: any, callBack?: Function, option?: any): any {
    this.http.post(baseUrl + `admin/document/lesson/${option}`, data, { observe: 'response', headers: this.headers }).subscribe(
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

  edit(data: any, option?: any, callBack?: Function): any {
    this.http.put(baseUrl + `admin/document/${option}`, data, { observe: 'response', headers: this.headers }).subscribe(
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
  
  getAll(option: any, callBack: Function): any {
    this.http.get(baseUrl + `admin/lesson/${option}`, { observe: 'response', headers: this.headers}).subscribe(
      response => {
        if (response.body) {
          let body: any = Object.assign({}, response.body);
          if (body) {
            if (callBack) {
              callBack(response.body);
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

  deleteDetail(id: any, callBack: Function): any {
    this.http.delete(baseUrl + `admin/document/${id}`, { observe: 'response', headers: this.headers}).subscribe(
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
