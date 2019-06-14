import {Component, OnInit} from '@angular/core';
import {UpdateDeviceCountService} from '../../../service/updateDeviceCount/update-device-count.service';
import {ActivatedRoute} from '@angular/router';
import {GetSingleDeviceService} from '../../../service/getSingleDevice/get-single-device.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-update-device',
  templateUrl: './update-device.component.html',
  styleUrls: ['./update-device.component.scss']
})
export class UpdateDeviceComponent implements OnInit {
  equipName;
  equipNum;
  installSite;
  selectedValue;
  equipSort;
  customName;
  propertyNum;
  specification;
  manufacturer;
  date;
  originalValue;
  netValue;
  equipType;
  user;
  currentId;

  constructor(
    private updateDeviceCountService: UpdateDeviceCountService,
    private activatedRoute: ActivatedRoute,
    private getSingleDeviceService: GetSingleDeviceService,
    private mes: NzMessageService
  ) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      res => {
        console.log(res);
        this.currentId = res.id;
        const getSingleDevicePara = {
          id: this.currentId
        };
        this.getSingleDeviceService.getSingleDevice(getSingleDevicePara).subscribe(
          result => {
            console.log(result);
            this.equipName = result.data.equipName;
            this.equipNum = result.data.equipNum;
            this.installSite = result.data.installSite;
            this.equipSort = result.data.equipSort;
            this.customName = result.data.customName;
            this.propertyNum = result.data.propertyNum;
            this.specification = result.data.specification;
            this.manufacturer = result.data.manufacturer;
            this.date = result.data.time;
            this.originalValue = result.data.originalValue;
            this.netValue = result.data.netlValue;
            this.user = result.data.user;
            this.equipType = result.data.equipType;
            this.selectedValue = result.data.status;
          },
          error => {
            console.log(error);
          }
        );
      }
    );
  }

  onChange(e) {
    console.log(this.date);
    console.log(this.date.getFullYear());
    const year = this.date.getFullYear();
    const mon = this.date.getMonth() + 1;
    const day = this.date.getDate();
    console.log(this.date.getMonth());
    console.log(this.date.getDay());
    console.log(this.date.getDate());
    console.log(this.date.toLocaleString());
    this.date = `${year}-${mon}-${day}`;
    console.log(this.date);
    console.log(e);
  }

  updateDevice(para) {
    this.updateDeviceCountService.updateDeviceCount(para).subscribe(
      res => {
        console.log(res);
        this.mes.success(res.msg);
      },
      err => {
        console.log(err);
      }
    );
  }
  submit() {
    if (!this.equipName) {
      this.mes.warning('设备名还没填写！');
      return;
    }
    const updateDevicePara = {
      id: this.currentId,
      customName: this.customName,
      propertyNum: this.propertyNum,
      equipType: this.equipType,
      specification: this.specification,
      manufacturer: this.manufacturer,
      time: this.date,
      originalValue: this.originalValue,
      netValue: this.netValue,
      user: this.user,
      status: this.selectedValue
    };
    this.updateDevice(updateDevicePara);
  }
}
