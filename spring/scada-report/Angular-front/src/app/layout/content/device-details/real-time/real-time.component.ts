import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-real-time',
  templateUrl: './real-time.component.html',
  styleUrls: ['./real-time.component.scss']
})
export class RealTimeComponent implements OnInit {
  radioValue = 'A';
  constructor() { }

  ngOnInit() {
  }

}
