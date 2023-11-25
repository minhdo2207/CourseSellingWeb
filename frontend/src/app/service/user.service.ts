import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../envirments/envirment.const';
import { AlertService } from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

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
  getAll(option: any, callBack: Function): any {
    let param = {};
    param = Object.assign({size: 10}, option);

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

  create(option: any, data: any, callBack: Function): any {
    this.http.post(baseUrl + `admin/user/role/${option}`, data, { observe: 'response', headers: this.headers}).subscribe(
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

  edit(option: any, data: any, callBack: Function): any {
    this.http.put(baseUrl + `admin/user/${option}`, data, { observe: 'response', headers: this.headers}).subscribe(
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

  getDetail(option: any, callBack: Function){
    this.http.get(baseUrl + `admin/user/${option}`, { observe: 'response', headers: this.headers }).subscribe(
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
    this.http.delete(baseUrl + `admin/user/${id}`, { observe: 'response', headers: this.headers}).subscribe(
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
