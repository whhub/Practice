import {Component, OnInit} from '@angular/core';
import {EChartOption} from 'echarts';
import {GetStartUpPercentService} from '../../../../service/getStartUpPercent/get-start-up-percent.service';


@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html',
  styleUrls: ['./bar.component.scss']
})
export class BarComponent implements OnInit {
  x_array = [];
  y_array = [];
  openPercent = [];
  closePercent = [];
  chartOption: EChartOption;

  constructor(
    private getStartUpPercentService: GetStartUpPercentService
  ) {
  }

  ngOnInit() {
    this.getStartUpPercentService.getStartUpPercent().subscribe(
      res => {
        console.log(res);
        this.x_array = Object.keys(res.data);
        this.openPercent = this.x_array.map(item => {
          return res.data[item];
        });
        this.closePercent = this.x_array.map(item => {
          return 100 - res.data[item];
        });
        this.chartOption = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            x: 'center',
            y: 'top',
            data: ['开机', '关机']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: this.x_array,
              axisLabel: {
                show: true,
                interval: 0,
                  rotate: 40,
                color: '#27B6Af',
                fontSize: 12
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              axisLabel: {
                formatter: '{value} %'
              },
              splitLine: {
                show: false
              }
            }
          ],
          series: [
            {
              name: '关机',
              type: 'bar',
              data: this.closePercent,
              stack: 'all',
              // tooltip: {
              //   trigger: 'item',
              //   formatter: '{c} %'
              // }
              itemStyle: {
                color: '#E51C23'
              }
            },
            {
              name: '开机',
              type: 'bar',
              data: this.openPercent,
              stack: 'all',
              barWidth: 40,
              barGap: '30%', // Make series be overlap
              // tooltip: {
              //   trigger: 'item',
              //   formatter: '{c} %'
              // }
              label: {
                normal: {
                  show: true,
                  position: 'top',
                  formatter: `{c}%`
                },
              },
              itemStyle: {
                color: '#259B24'
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
