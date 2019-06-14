import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-task',
  templateUrl: './my-task.component.html',
  styleUrls: ['./my-task.component.scss']
})
export class MyTaskComponent implements OnInit {
  inType;
  ouType;
  constructor() { }

  ngOnInit() {
  }

}
