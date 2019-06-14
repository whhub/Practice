import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-oeemodeling',
  templateUrl: './oeemodeling.component.html',
  styleUrls: ['./oeemodeling.component.scss']
})
export class OEEModelingComponent implements OnInit {
  radioValue;
  selectedValue1 = '可用时间';
  currentItemIndex = 0;
  mapOfExpandData: { [key: string]: boolean } = {};
  listOfData = [
    {
      id: 1,
      name: 'John Brown',
      age: 32,
      expand: false,
      address: 'New York No. 1 Lake Park',
      description: 'My name is John Brown, I am 32 years old, living in New York No. 1 Lake Park.'
    },
    {
      id: 2,
      name: 'Jim Green',
      age: 42,
      expand: false,
      address: 'London No. 1 Lake Park',
      description: 'My name is Jim Green, I am 42 years old, living in London No. 1 Lake Park.'
    },
    {
      id: 3,
      name: 'Joe Black',
      age: 32,
      expand: false,
      address: 'Sidney No. 1 Lake Park',
      description: 'My name is Joe Black, I am 32 years old, living in Sidney No. 1 Lake Park.'
    }
  ];
  source = [
    {
      name: '可用时间',
      index: 0
    },
    {
      name: '劳动生产率',
      index: 1
    },
    {
      name: '质量',
      index: 2
    }
  ];
  constructor() { }

  ngOnInit() {
  }

}
