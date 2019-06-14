import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-control-status',
  templateUrl: './control-status.component.html',
  styleUrls: ['./control-status.component.scss']
})
export class ControlStatusComponent implements OnInit {
  selectedValue;
  listOfData = [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York'
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 40,
      address: 'London'
    }
  ];
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
  checkOptions = [
    { label: '运行', value: 'run' },
    { label: '待机', value: 'off', checked: true },
    { label: '报警', value: 'warning' }
  ];
  constructor() { }
  log(value): void {
    console.log(value);
  }
  ngOnInit() {
  }

}
