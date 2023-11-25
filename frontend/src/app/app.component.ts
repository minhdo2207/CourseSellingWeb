import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { AlertService } from './service/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isAuthentication = false;
  username = '';
  password = '';
  token: any;

  constructor(
    private authSrv: AuthService,
    private alertSrv: AlertService
  ){}

  ngOnInit(){
    this.authSrv.isAuthentication.subscribe(value => this.isAuthentication = value);
    if(localStorage.getItem('token')){
      this.authSrv.isAuthentication.next(true);
    }
  }
  
  // xử lý đăng nhập
  login() {
    this.authSrv.login(
      {username: this.username, password: this.password},
      (res: any) => {
        if(res){
          this.isAuthentication = true;
          this.authSrv.isAuthentication.next(true);
          this.alertSrv.showSuccess('Đăng nhập thành công', 'Thành công!');
        }
      }
    )
  }

  logout(){
    localStorage.removeItem('token');
    this.authSrv.isAuthentication.next(false);
    this.alertSrv.showSuccess('Đăng xuất thành công', 'Thành công!');
  }
}
