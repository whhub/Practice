import { Component, OnInit } from '@angular/core';
import {GetDeviceCountHomeService} from '../../../service/getDeviceCount/get-device-count-home.service';
import {DeleteDeviceCountService} from '../../../service/deleteDeviceCount/delete-device-count.service';
import {NzMessageService} from 'ng-zorro-antd';
import {SyncDeviceService} from '../../../service/syncDevice/sync-device.service';
import {GetLastFiveYearCountService} from '../../../service/getLastFiveYearCount/get-last-five-year-count.service';

@Component({
  selector: 'app-device-count',
  templateUrl: './device-count.component.html',
  styleUrls: ['./device-count.component.scss']
})
export class DeviceCountComponent implements OnInit {
  selectedValue = '';
  propertyName;
  sortName: string | null = null;
  sortValue: string | null = null;
  searchAddress: string;
  listOfSearchName: string[] = [];
  searchSource = [];
  listOfData = [
    ];
  listOfDisplayData = [
    ...this.listOfData
  ];
  constructor(
    private getDeviceCountHomeService: GetDeviceCountHomeService,
    private deleteDeviceCountService: DeleteDeviceCountService,
    private mes: NzMessageService,
    private syncDeviceService: SyncDeviceService,
    private getLastFiveYearCountService: GetLastFiveYearCountService
  ) { }

  ngOnInit() {
   this.getDeviceCountHome();
  }
  sort(sort: { key: string; value: string }): void {
    console.log(sort);
    this.sortName = sort.key;
    this.sortValue = sort.value;
    this.search();
  }

  filter(listOfSearchName: string[], searchAddress: string): void {
    this.listOfSearchName = listOfSearchName;
    this.searchAddress = searchAddress;
    this.search();
  }

  search(): void {
    /** filter data **/
    const filterFunc = (item) =>
      (this.searchAddress ? item.address.indexOf(this.searchAddress) !== -1 : true) &&
      (this.listOfSearchName.length ? this.listOfSearchName.some(name => item.name.indexOf(name) !== -1) : true);
    const data = this.listOfData.filter(item => filterFunc(item));
    /** sort data **/
    if (this.sortName && this.sortValue) {
      this.listOfDisplayData = data.sort((a, b) =>
        this.sortValue === 'ascend'
          ? a[this.sortName] > b[this.sortName]
          ? 1
          : -1
          : b[this.sortName] > a[this.sortName]
          ? 1
          : -1
      );
    } else {
      this.listOfDisplayData = data;
    }
  }
  delete(e) {
    console.log(e);
    const id = e.currentTarget.getAttribute('data-id');
    const deleteDeviceCountPara = {
      id: id
    };
    this.deleteDeviceCountService.deleteDeviceCount(deleteDeviceCountPara).subscribe(
      res => {
        console.log(res);
        this.mes.success('操作成功');
        this.getDeviceCountHome();
      },
      err => {
        console.log(err);
      }
    );
  }
  getDeviceCountHome() {
    this.getDeviceCountHomeService.getDeviceCountHome().subscribe(
      res => {
        console.log(res);
        this.listOfData = res.data.map((item, index) => {
          return {
            number: index + 1,
            id : item.id,
            category: item.equipSort,
            propertyNumber: item.propertyNum ,
            deviceNumber: item.equipNum,
            name: item.equipName,
            size: '10.2.34.5',
            deviceModel: 'MVCd2123',
            factory: item.manufacturer || '未填写',
            date: item.time,
            propertyOrigin: item.originalValue,
            propertyNow: item.netValue,
            place: item.installSite,
            workshop: item.user,
            deviceStatus: '在用',
          };
        });
        this.listOfDisplayData = [
          ...this.listOfData
        ];
        this.searchSource = this.listOfDisplayData;
      },
      err => {
        console.log(err);
      }
    );
  }
  syncDevice() {
    this.syncDeviceService.syncDevice().subscribe(
      res => {
        console.log(res);
        if (res.code !== 0) {
          this.mes.warning(res.msg);
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.listOfData = res.data.map((item, index) => {
          return {
            number: index + 1,
            id : item.id,
            category: item.equipSort,
            propertyNumber: item.propertyNum ,
            deviceNumber: item.equipNum,
            name: item.equipName,
            size: '10.2.34.5',
            deviceModel: 'MVCd2123',
            factory: item.manufacturer || '未填写',
            date: item.time,
            propertyOrigin: item.originalValue,
            propertyNow: item.netValue,
            place: item.installSite,
            workshop: item.user,
            deviceStatus: '在用',
          };
        });
        this.listOfDisplayData = [
          ...this.listOfData
        ];
        this.searchSource = this.listOfDisplayData;
      },
      err => {
        console.log(err);
      }
    );
  }
  fuzzyQuery() {
    console.log(this.propertyName);
    if ( !this.selectedValue && !this.propertyName) {
      this.listOfDisplayData = this.searchSource;
      return;
    }
    if (!(this.propertyName && this.selectedValue)) {
      this.listOfDisplayData = this.searchSource.filter(item => {
        return item.name === this.propertyName || item.deviceStatus === this.selectedValue;
      });
      return;
    }
    this.listOfDisplayData = this.searchSource.filter(item => {
      return item.name === this.propertyName && item.deviceStatus === this.selectedValue;
    });
  }
}
