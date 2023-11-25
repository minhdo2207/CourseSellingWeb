import { Component, ViewChild } from "@angular/core";
import { ChartComponent } from "ng-apexcharts";

import {
  ApexNonAxisChartSeries,
  ApexResponsive,
  ApexChart,
  ApexLegend,
  ApexTitleSubtitle
} from "ng-apexcharts";
import { ReportService } from "src/app/service/report.service";

export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  legend: ApexLegend;
  labels: any;
  title: ApexTitleSubtitle
};

@Component({
  selector: 'app-pie',
  templateUrl: './pie.component.html',
  styleUrls: ['./pie.component.scss']
})
export class PieComponent {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions> | any;

  dataChart:number[] = [];

  constructor(
    private reportSrv: ReportService
  ) {
    //get data
    this.reportSrv.class(
      {sortDir: 'desc'},
      (res: any) => {
        if(res){
          //handle data
          let a = 0;
          let b = 0;
          let c = 0;
          res.elements.forEach((item: any) => {
            if(item.status == 1) a++;
            if(item.status == 2) b++;
            if(item.status == 0) c++;
          });
          this.dataChart.push(a,b,c);
        }
      }
    )
  }

  ngOnInit(){
    this.chartOptions = {
      series: this.dataChart,
      title: {
        text: "Tình trạng đăng ký khóa học"
      },
      chart: {
        width: 380,
        type: "pie"
      },
      labels: ["Đã tham gia", "Đã hủy", "Đang Chờ Lớp"],
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ],
      legend: {
        position: "right",
        horizontalAlign: "center"
      }
    };
  }
}
