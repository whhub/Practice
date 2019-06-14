import {Component, OnInit} from '@angular/core';
import {EChartOption} from 'echarts';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  chartOption: EChartOption = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross'
      }
    },
    toolbox: {
      feature: {
        dataView: {show: true, readOnly: false},
        magicType: {show: true, type: ['line', 'bar']},
        restore: {show: true},
        saveAsImage: {show: true}
      }
    },
    legend: {
      data: ['关机率', '开机率', '开机数']
    },
    xAxis: [
      {
        type: 'category',
        data: ['注塑机', '一体机', '打印机', '空调', '饮水机'],
        axisPointer: {
          type: 'shadow'
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: '开机数',
        min: 0,
        max: 40,
        interval: 50,
        axisLabel: {
          formatter: '{value} 台'
        }
      },
      {
        type: 'value',
        name: '开机百分比',
        min: 0,
        max: 100,
        interval: 20,
        axisLabel: {
          formatter: '{value} %'
        }
      }
    ],
    series: [
      {
        name: '关机率',
        type: 'bar',
        stack: '广告',
        yAxisIndex: 1,
        data: [28.6, 55.6, 56.2, 66.7, 57.1],
        // label: {
        //   normal: {
        //     show: true,
        //     position: 'inside'
        //   }
        // },
      },
      {
        name: '开机率',
        type: 'bar',
        stack: '广告',
        yAxisIndex: 1,
        data: [71.4, 44.4, 43.8, 33.3, 42.9],
        label: {
          normal: {
            show: true,
            position: 'inside',
            formatter: `{c}%`
          },
          formatter: `{c}%`
        },
      },
      {
        name: '开机数',
        type: 'line',
        data: [5, 4, 7, 2, 6],
        label: {
          normal: {
            show: true,
            position: 'top'
          }
        },
      }
    ]
  };

  constructor() {
  }

  ngOnInit() {
  }

}
