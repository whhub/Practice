import { Component, OnInit } from '@angular/core';
import {EChartOption} from 'echarts';

@Component({
  selector: 'app-oee',
  templateUrl: './oee.component.html',
  styleUrls: ['./oee.component.scss']
})
export class OEEComponent implements OnInit {
  chartOption: EChartOption = {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: [
      {
        name: '比例',
        type: 'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        label: {
          normal: {
            show: false,
            position: 'center'
          },
          emphasis: {
            show: true,
            textStyle: {
              fontSize: '20',
              fontWeight: 'bold'
            }
          }
        },
        labelLine: {
          normal: {
            show: false
          }
        },
        data: [
          {
            value: 335,
            name: '加工',
            itemStyle: {
              color: '#219020'
            }

          },
          {
            value: 234,
            name: '待机',
            itemStyle: {
              color: '#ff8d05'
            }
          },
          {
            value: 135,
            name: '故障',
            itemStyle: {
              color: '#e21e25'
            }
          }
        ]
      }
    ]
  };
  constructor() { }

  ngOnInit() {
  }

}
