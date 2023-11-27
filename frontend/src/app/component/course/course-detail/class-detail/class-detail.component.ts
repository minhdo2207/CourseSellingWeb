import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClassService } from 'src/app/service/class.service';

@Component({
  selector: 'app-class-detail',
  templateUrl: './class-detail.component.html',
  styleUrls: ['./class-detail.component.scss']
})
export class ClassDetailComponent {
  classs: any;
  classId: any;

  constructor(
    private classService: ClassService,
    private route: ActivatedRoute,
  ) {
    this.classId = this.route.snapshot.paramMap.get('id');
    
  }

  getAllData(){
    this.classService.getDetail(this.classId, (res: any) => {
      this.classs = res;
    })
  }

  
  ngOnInit() { 
    this.getAllData();
  }

}
