import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-calcu-rule',
  templateUrl: './add-calcu-rule.component.html',
  styleUrls: ['./add-calcu-rule.component.scss']
})
export class AddCalcuRuleComponent implements OnInit {
   value;
  inputValue;
  mapOfExpandData: { [key: string]: boolean } = {};
  mapOfExpandData1: { [key: string]: boolean } = {};
  listOfData = [
  {id: 1}
  ];
  listOfData2 = [
    {id: 1}
  ];
  constructor() { }

  ngOnInit() {
  }

}
