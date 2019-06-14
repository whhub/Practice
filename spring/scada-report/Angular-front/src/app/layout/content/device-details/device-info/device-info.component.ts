import { Component, OnInit , Input, OnChanges } from '@angular/core';
import {SimpleChanges} from '@angular/core';
import {GetDevicebyNumService} from '../../../../service/getDevicebyNum/get-deviceby-num.service';
import {GetDeviceListService} from '../../../../service/getDeviceList/get-device-list.service';

@Component({
  selector: 'app-device-info',
  templateUrl: './device-info.component.html',
  styleUrls: ['./device-info.component.scss']
})
export class DeviceInfoComponent implements OnInit , OnChanges {
@Input() deviceNum;
    currentBrandId: number;
    currentCustomName: string;
    currentEquipName: string;
    currentEquipNum: string;
    currentEquipSort: string;
    currentEquipType: string;
    currentId: number;
    currentInstallSite: string;
    currentManufacturer: string;
    currentNetValue: string;
    currentOriginalValue: string;
    currentPropertyName: string;
    currentPropertyNum: string;
    currentSpecification: string;
    currentStatus: string;
    currentTime: string;
    currentTypeId: number;
    currentUser: string;
    openTime;
  closeTime;
  openPercent;
  closePercent;
  constructor(
    private getDevicebyNumService: GetDevicebyNumService,
    private getDeviceListService: GetDeviceListService
  ) { }

  ngOnInit() {
    this.getDeviceListService.getDeviceList().subscribe(
      res => {
        console.log(res);
        // 获取第一个类别下的第一个设备编号
        const equipArray = Object.keys(res.data);
        console.log(equipArray);
         const source = equipArray.map((item, index) => {
          return {
            name: item,
            count: Object.keys(res.data[item]).length,
            isOpen: false,
            index: index,
            // children: [
            //   {
            //     service_name: '水压测试仪01'
            //   },
            //   {
            //     service_name: '水压测试仪01'
            //   },
            //   {
            //     service_name: '水压测试仪01'
            //   },
            //   {
            //     service_name: '水压测试仪01'
            //   }
            // ],
            children : Object.keys(res.data[item]).map(item0 => {
              return {
                service_name: item0,
                deviceNum: res.data[item][item0]
              };
            })
          };
        });
         this.deviceNum = source[0].children[0].deviceNum;
         console.log(this.deviceNum);
         const getDevicebyNumPara = {
           deviceNum: this.deviceNum
         };
       this.getDevicebyNum(getDevicebyNumPara);
         },
      err => {
        console.log(err);
      }
    );
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    console.log(changes.deviceNum.currentValue);
    this.deviceNum = changes.deviceNum.currentValue;
    const getDevicebyNumPara = {
      deviceNum: this.deviceNum
    };
    this.getDevicebyNum(getDevicebyNumPara);
  }
  getDevicebyNum(para) {
    this.getDevicebyNumService.getDevicebyNum(para).subscribe(
      result => {
        console.log(result);
        this.currentBrandId = result.data.brandId;
        this.currentCustomName = result.data.customName;
        this.currentEquipNum = result.data.equipNum;
        this.currentEquipName = result.data.equipName;
        this.currentEquipSort = result.data.equipSort;
        this.currentEquipType = result.data.equipType;
        this.currentId = result.data.id;
        this.currentInstallSite = result.data.installSite;
        this.currentManufacturer = result.data.manufacturer;
        this.currentNetValue = result.data.netValue;
        this.currentOriginalValue = result.data.originalValue;
        this.currentPropertyName = result.data.propertyName;
        this.currentPropertyNum = result.data.propertyNum;
        this.currentSpecification = result.data.specification;
        this.currentStatus = result.data.status;
        this.currentTime = result.data.time;
        this.currentTypeId = result.data.typeId;
        this.currentUser = result.data.user;
        const timeObj = result.data.timeMap;
        this.openTime = `${Math.floor(timeObj.startup / 60) }小时${timeObj.startup % 60}分钟`;
        console.log(this.openTime);
        this.closeTime = `${Math.floor(timeObj.close / 60) }小时${timeObj.close % 60}分钟`;
        console.log(this.closeTime);
        this.openPercent = timeObj.startup / (timeObj.startup + timeObj.close) * 100;
        this.closePercent = timeObj.close / (timeObj.startup + timeObj.close) * 100;
      },
      error => {
        console.log(error);
      }
    );
  }
}
