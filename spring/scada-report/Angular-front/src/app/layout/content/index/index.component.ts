import {Component, OnInit} from '@angular/core';
import {GetDeviceStatusWatchInfoService} from '../../../service/getDeviceStatusWatchInfo/get-device-status-watch-info.service';
import {GetIndexStatusInfoService} from '../../../service/getIndexStatusInfo/get-index-status-info.service';


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  runCount = 0;
  closeCount = 0;
  errorCount = 0;
  listOfData = [
    // {
    //   name: '水压测试仪01',
    //   time: '2019-04-30 08:30',
    //   status: 'online',
    //   change: 'up',
    //   value: '2.34%'
    // },
    // {
    //   name: '水压测试仪02',
    //   time: '2019-04-30 08:50',
    //   status: 'offline',
    //   change: 'down',
    //   value: '5.34%'
    // },
    // {
    //   name: '水压测试仪03',
    //   time: '2019-04-30 09:30',
    //   status: 'wait',
    //   change: 'up',
    //   value: '2.99%'
    // },
    // {
    //   name: '水压测试仪04',
    //   time: '2019-04-30 10:30',
    //   status: 'error',
    //   change: 'down',
    //   value: '5.70%'
    // }
  ];
  switchValue = true;
  constructor(
    private getDeviceStatusWatchInfoService: GetDeviceStatusWatchInfoService,
    private getIndexStatusInfoService: GetIndexStatusInfoService
  ) {
  }

  ngOnInit() {
    this.getDeviceStatusWatchInfoService.getDeviceStatusWatchInfo().subscribe(
      res => {
        console.log(res);
        this.runCount = res.data['开机'];
        this.closeCount = res.data['关机'];
        this.errorCount = res.data['故障'];
      },
      err => {
        console.log(err);
      }
    );
    this.getIndexStatusInfoService.getIndexStatusInfo().subscribe(
      res => {
        console.log(res);
        this.listOfData = res.data.map(item => {
          return {
            name: item.equipName,
            time: item.date,
            status: item.stateCode,
            change: item.isUp,
            value: item.openRatio,
            deviceNum: item.deviceNum,
            openTime: item.openTimeNow
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }

}
