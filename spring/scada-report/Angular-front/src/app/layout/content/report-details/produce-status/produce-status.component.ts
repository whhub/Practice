import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-produce-status',
  templateUrl: './produce-status.component.html',
  styleUrls: ['./produce-status.component.scss']
})
export class ProduceStatusComponent implements OnInit {
  dataSet = [
    {
      status    : 0,
      diviceId   : 'SY-12031',
      runTime    : '03:02:15',
      runTimePercent    : '32%' ,
      offTime: '02:02:55',
      offTimePercent: '20%',
      errTime: '01:22:15',
      errTimePercent: '2%',
    },
    {
      status    : 1,
      diviceId   : 'DL-12031',
      runTime    : '03:02:15',
      runTimePercent    : '72%' ,
      offTime: '02:02:55',
      offTimePercent: '20%',
      errTime: '01:22:15',
      errTimePercent: '2%',
    },
    {
      status    : 2,
      diviceId   : 'SY-12031',
      runTime    : '03:02:15',
      runTimePercent    : '32%' ,
      offTime: '02:02:55',
      offTimePercent: '40%',
      errTime: '01:22:15',
      errTimePercent: '2%',
    },
    {
      status    : 3,
      diviceId   : 'DL-12031',
      runTime    : '03:02:15',
      runTimePercent    : '32%' ,
      offTime: '02:02:55',
      offTimePercent: '20%',
      errTime: '01:22:15',
      errTimePercent: '32%',
    }
  ];
  selectedValue;
  constructor() { }

  checkOptions = [
    { label: '运行', value: 'run' },
    { label: '待机', value: 'off', checked: true },
    { label: '报警', value: 'warning' }
  ];
  ngOnInit() {
  }
  log(value): void {
    console.log(value);
  }
}
