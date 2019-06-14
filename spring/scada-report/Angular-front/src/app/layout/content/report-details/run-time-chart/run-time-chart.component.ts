import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-run-time-chart',
  templateUrl: './run-time-chart.component.html',
  styleUrls: ['./run-time-chart.component.scss']
})
export class RunTimeChartComponent implements OnInit {
  dataSet = [
    {
      status    : 0,
      diviceId   : 'SY-12031',
      availability: 0,
      emergency: 'ARMED',
      execution: 1,
      mode: 1,
      sysStatus: 0
    },
    {
      status    : 1,
      diviceId   : 'DL-12031',
      availability: 1,
      emergency: 'ARMED',
      execution: 0,
      mode: 3,
      sysStatus: 1
    },
    {
      status    : 2,
      diviceId   : 'SY-12031',
      availability: 0,
      emergency: 'ARMED',
      execution: 1,
      mode: 2,
      sysStatus: 1
    },
    {
      status    : 3,
      diviceId   : 'DL-12031',
      availability: 0,
      emergency: 'ARMED',
      execution: 0,
      mode: 0,
      sysStatus: 0
    }
  ];
  constructor() { }

  ngOnInit() {
  }

}
