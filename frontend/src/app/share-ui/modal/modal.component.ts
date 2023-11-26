import { Component } from '@angular/core';
import { Item } from 'src/app/const/const';
import { ModalService } from 'src/app/service/modal.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent {
  itemData: Item = {
    isShow: false,
    text: 'hihi',
  }

  constructor(private modalService: ModalService) {}

  ngOnInit() {
    this.modalService.data$.subscribe((value) => {
      this.itemData = value;
    });

    console.log('itemData', this.itemData);
    
  }

  closeModal() {
    this.modalService.updateStatusModal(false, 'hihi');
  }
}
