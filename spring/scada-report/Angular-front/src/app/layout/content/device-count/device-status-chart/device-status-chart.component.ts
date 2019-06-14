import {Component, OnInit} from '@angular/core';
import {EChartOption} from 'echarts';
import {GetStatusChartInfoService} from '../../../../service/getStatusChartInfo/get-status-chart-info.service';

@Component({
  selector: 'app-device-status-chart',
  templateUrl: './device-status-chart.component.html',
  styleUrls: ['./device-status-chart.component.scss']
})
export class DeviceStatusChartComponent implements OnInit {
  chartOption: EChartOption;


  constructor(
    private getStatusChartInfoService: GetStatusChartInfoService
  ) {
  }

  ngOnInit() {
    this.getStatusChartInfoService.getStatusChartInfo().subscribe(
      res => {
        console.log(res);
        console.log(res.data);
        const cateArray = Object.keys(res.data);
        const valueArray: any[] = Object.values(res.data).map((item, index) => {
          return {
            value: item, name: cateArray[index]
          };
        });
        console.log(valueArray);
        this.chartOption = {
          title: {
            show: false,
            text: '当前设备状态统计分析',
            // subtext: '纯属虚构',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: cateArray
          },
          series: [
            {
              name: '设备状态',
              type: 'pie',
              radius: '55%',
              center: ['50%', '60%'],
              data: valueArray,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };
      },
      err => {
        console.log(err);
      }
    );
  }

}
