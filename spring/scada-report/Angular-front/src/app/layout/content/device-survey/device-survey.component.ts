import { Component, OnInit } from '@angular/core';
import {trigger, state, transition, style, animate} from '@angular/animations';

@Component({
  selector: 'app-device-survey',
  templateUrl: './device-survey.component.html',
  animations     : [
    trigger('showState', [
      state('show', style({
        opacity: '0',
        height : 0
      })),
      state('hidden', style({
        opacity: '1',
        height : '*'
      })),
      transition('hidden => show', animate('150ms ease-in')),
      transition('show => hidden', animate('150ms ease-out'))
    ])
  ],
  styleUrls: ['./device-survey.component.scss']
})
export class DeviceSurveyComponent implements OnInit {
  selectedValue;
  isShowtoolBox = true;
  constructor() { }
  checkOptions = [
    { label: '运行', value: 'run' },
    { label: '待机', value: 'off', checked: true },
    { label: '报警', value: 'warning' }
  ];
  dataSet = [
    {
      key    : '1',
      name   : '沈阳机床i5',
      oee    : 54,
      use: 54,
      ablity: 99,
      count: 8,
      onTime: 42,
      offTime: 15,
      errTime: 10,
      product: 42.2,
      setUp: 30.8,
      tearDown: 12.9,
      currentProgram: 'open.js',
      maintenance: 0.3
    }
  ];
  ngOnInit() {
  }
  log(value): void {
    console.log(value);
  }
}
