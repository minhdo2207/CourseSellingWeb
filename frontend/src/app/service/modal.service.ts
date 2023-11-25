import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Item } from '../const/const';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  item: Item = {
    isShow: false,
    text: 'Thông báo nè'
  }

  private isShowModal = new BehaviorSubject<Item>(this.item);
  public data$ = this.isShowModal.asObservable();

  updateStatusModal(isShowModal: boolean, textModal: string) {
    let newData: Item = {
      isShow: isShowModal,
      text: textModal
    }
      
    this.isShowModal.next(newData);
  }
}
