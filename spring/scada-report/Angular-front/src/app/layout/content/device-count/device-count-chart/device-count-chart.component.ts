import {Component, OnInit} from '@angular/core';
import {EChartOption} from 'echarts';
import {GetLastFiveYearCountService} from '../../../../service/getLastFiveYearCount/get-last-five-year-count.service';

@Component({
  selector: 'app-device-count-chart',
  templateUrl: './device-count-chart.component.html',
  styleUrls: ['./device-count-chart.component.scss']
})
export class DeviceCountChartComponent implements OnInit {
  chartOption: EChartOption ;
  constructor(
    private getLastFiveYearCountService: GetLastFiveYearCountService
  ) {
  }

  ngOnInit() {
    this.getLastFiveYearCountService.getLastFiveYearCount().subscribe(
      res => {
        console.log(res);
        console.log(res.data.total);
        const timeArray = Object.keys(res.data);
        const valueArray = Object.values(res.data);
        this.chartOption =  {
          tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          xAxis: {
            type: 'category',
            data: timeArray
          },
          yAxis: {
            type: 'value'
          },
          grid: {
            top: 30
          },
          series: [{
            data: valueArray,
            type: 'bar',
            itemStyle: {
              normal: {
                // 随机显示//color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}

                // 定制显示（按顺序）
                color: function (params) {
                  const colorList = ['#C33531', '#27B6AF', '#64BD3D', '#EE9201', '#29AAE3',
                    '#B74AE5', '#0AAF9F', '#E89589', '#16A085', '#4A235A', '#C39BD3 ', '#F9E79F',
                    '#BA4A00', '#ECF0F1', '#616A6B', '#EAF2F8', '#4A235A', '#3498DB'];
                  return colorList[params.dataIndex];
                }
              },
            },
          }]
        };
      },
      err => {
        console.log(err);
      }
    );
  }

}
