import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-real-time-data',
  templateUrl: './real-time-data.component.html',
  styleUrls: ['./real-time-data.component.scss']
})
export class RealTimeDataComponent implements OnInit {
data = [1, 2, 3];
  constructor() { }

  ngOnInit() {
  }

}
