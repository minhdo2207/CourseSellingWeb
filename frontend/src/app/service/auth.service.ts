import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { baseUrl } from '../envirments/envirment.const';
import { AlertService } from './alert.service';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  protected http: HttpClient;
  constructor(
    private injector: Injector,
    private alertSrv: AlertService
  ) {
    this.http = injector.get(HttpClient);
  }

  public isAuthentication = new BehaviorSubject<boolean>(false);

  login(data: any, callBack: Function): any {
    this.http.post(baseUrl + 'auth/signin', data, {observe: 'response'}).subscribe(
      (response) => {
        if (response.body) {
          let body: any = Object.assign({}, response.body);
          localStorage.setItem('token', body.data.token);
          callBack(body);
        }
      },
      (error) => {
        if (callBack) {
          callBack(null);
          this.alertSrv.showError('Đăng nhập thất bại', 'Lỗi!');
        }
      }
    )
  }
}
