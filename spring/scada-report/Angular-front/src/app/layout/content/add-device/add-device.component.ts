import { Component, OnInit } from '@angular/core';
import {UpdateDeviceCountService} from '../../../service/updateDeviceCount/update-device-count.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-device',
  templateUrl: './add-device.component.html',
  styleUrls: ['./add-device.component.scss']
})
export class AddDeviceComponent implements OnInit {
  value1 = '水压测试仪01';
  value2 = '未知';
  selectedValue;
  date;
  constructor(
    private updateDeviceCountService: UpdateDeviceCountService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
  }
  onChange(e) {
    console.log(e);
  }
}
