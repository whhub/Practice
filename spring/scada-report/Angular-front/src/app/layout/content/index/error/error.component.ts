import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  timeData = [
    '2009/7/31 8:00',
    '2009/7/31 9:00',
    '2009/7/31 10:00',
    '2009/7/31 11:00',
    '2009/7/31 12:00',
    '2009/7/31 13:00',
    '2009/7/31 14:00',
    '2009/7/31 15:00',
    '2009/7/31 16:00',
    '2009/7/31 17:00',
    '2009/7/31 18:00',
    '2009/7/31 19:00',
    '2009/7/31 20:00',
    '2009/7/31 21:00',
    '2009/7/31 22:00',
    '2009/7/31 23:00',
    '2009/8/1 0:00',
    '2009/8/1 1:00',
    '2009/8/1 2:00',
    '2009/8/1 3:00',
    '2009/8/1 4:00',
    '2009/8/1 5:00',
    '2009/8/1 6:00',
    '2009/8/1 7:00',
  ];
  chartOption;
  constructor() {
  }

  ngOnInit() {
    this.timeData = this.timeData.map((str) => {
      return str.replace('2009/', '');
    });
    this.chartOption = {
      tooltip: {
        show: false,
        trigger: 'axis',
        axisPointer: {
          animation: false
        }
      },
      axisPointer: {
        link: {xAxisIndex: 'all'}
      },
      grid: [
        {  left: '0',
          right: '0',
          bottom: '0',
          top: '0',
          containLabel: true
        }],
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          axisLine: {onZero: true},
          data: this.timeData,
          show: false
        },
      ],
      yAxis: [
        {
          name: '负载(mm)',
          type: 'value',
          inverse: true,
          show: false
        }
      ],
      series: [
        {
          name: '负载',
          type: 'line',
          symbolSize: 0,
          hoverAnimation: false,
          lineStyle: {
            color: '#ff8d05'
          },
          data: [
            0.042, 0.038, 0.209, 0.133, 0.059, 0.04,
            0.032, 0.042, 0.092, 0.173, 0.076, 0.062,
            0.0676, 0.166, 0.075, 0.196, 0.028, 0.181]
        }
      ]
    };
  }


}
