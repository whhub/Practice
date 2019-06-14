import {Component, OnInit} from '@angular/core';
import {EChartOption} from 'echarts';
import {GetDeviceCatePercentService} from '../../../../service/getDeviceCatePercent/get-device-cate-percent.service';
import {__param} from 'tslib';

@Component({
  selector: 'app-pie',
  templateUrl: './pie.component.html',
  styleUrls: ['./pie.component.scss']
})
export class PieComponent implements OnInit {
  chartOption: EChartOption;
  x_array = [];
  y_array = [];


  constructor(
    private getDeviceCatePercentService: GetDeviceCatePercentService
  ) {}

  ngOnInit() {
    this.getDeviceCatePercentService.getDeviceCatePercent().subscribe(
      res => {
        console.log(res);
        this.x_array = Object.keys(res.data);
        console.log(this.x_array);
        this.y_array = this.x_array.map(item => {
          return {value: res.data[item], name: item};
        });
        this.chartOption = {
          title: {
            show: false,
            text: '南丁格尔玫瑰图',
            subtext: '纯属虚构',
            // x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          grid: {
            top: '100',
            left: '100',
            right: '100',
            bottom: '100',
            // bottom: 0,
          },
          legend: {
            show: true,
            x: 'center',
            y: 'bottom',
            // y: 'left',
            data: this.x_array,
          },
          toolbox: {
            show: false,
            feature: {
              mark: {show: false},
              dataView: {show: true, readOnly: false},
              magicType: {
                show: true,
                type: ['pie']
              },
              restore: {show: true},
              saveAsImage: {show: true}
            }
          },
          series: [
            {
              name: '面积模式',
              type: 'pie',
              radius: [30, 80],
              center: ['50%', '40%'],
              roseType: 'area',
              data: this.y_array,
              itemStyle: {
                color : (param) => {
                  const colorList = [
                    '#339966', '#27B6AF', '#00c1d1', '#FF8C00', '#FF6633', '#FF9999', '#CC00FF', '#6633FF', '#006633',
                  ];
                  return colorList[param.dataIndex];
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
