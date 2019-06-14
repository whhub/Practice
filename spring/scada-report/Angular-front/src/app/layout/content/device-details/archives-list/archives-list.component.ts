import {Component, Input, OnInit, OnChanges} from '@angular/core';
import {ShowArchivesOperationService} from '../../../../service/showArchivesOperation/show-archives-operation.service';
import {GetDocListService} from '../../../../service/getDocList/get-doc-list.service';
import {SimpleChanges} from '@angular/core';
import {GetDocListByDeviceNumService} from '../../../../service/getDocListByDeviceNum/get-doc-list-by-device-num.service';
import {DownLoadDocService} from '../../../../service/downLoadDoc/down-load-doc.service';
import {NzMessageService} from 'ng-zorro-antd';
import {GetDeviceListService} from '../../../../service/getDeviceList/get-device-list.service';

@Component({
  selector: 'app-archives-list',
  templateUrl: './archives-list.component.html',
  styleUrls: ['./archives-list.component.scss']
})
export class ArchivesListComponent implements OnInit , OnChanges {
  @Input() deviceNum;
  currentTab = 'all';
  flag = true;
  archiveslist = [];
  firstDeviceNum;
  constructor(
    private showArchivesOperationService: ShowArchivesOperationService,
    private getDocListService: GetDocListService,
    private getDocListByDeviceNumService: GetDocListByDeviceNumService,
    private downLoadDocService: DownLoadDocService,
    private mes: NzMessageService,
    private getDeviceListService: GetDeviceListService
  ) { }

  ngOnInit() {
    console.log(this.deviceNum);
    this.getDeviceListService.getDeviceList().subscribe(
      res => {
        console.log(res);
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
        this.firstDeviceNum = source[0].children[0].deviceNum;
        const getDocListByDeviceNumPara = {
          deviceNum: this.firstDeviceNum
        };
        console.log(getDocListByDeviceNumPara);
      },
      err => {
        console.log(err);
      }
    );
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    const deviceNum = changes.deviceNum.currentValue;
    if (!deviceNum) {
      return;
    }
    console.log(deviceNum);
    const getDocListByDeviceNumPara = {
      deviceNum: this.deviceNum
    };
    console.log(getDocListByDeviceNumPara);
    console.log(deviceNum);
    this.getDocListByDeviceNumService.getDocListetDocListByDeviceNum(getDocListByDeviceNumPara).subscribe(
      res => {
        console.log(res);
        this.archiveslist = res.data.map(item => {
          return {
            author: item.author,
            brand: item.brand,
            createTime: item.createTime,
            docName: item.docName,
            downloadNum: item.downloadNum,
            id: item.id,
            introduction: item.introduction,
            label: item.label,
            type: item.type,
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }

  showOperation() {
    console.log('点击');
    this.showArchivesOperationService.showArchivesOperation.next(false);
    // this.showArchivesOperationService.showArchivesOperation.next(false);
  }
  downLoad(e) {
   const typeId = e.currentTarget.getAttribute('data-typeId');
   const docName = e.currentTarget.getAttribute('data-docName');
   const brandId = e.currentTarget.getAttribute('data-brandId');
   const downLoadDocPara = {
     docName: docName,
     typeId: typeId,
     brandId: brandId
   };
   this.downLoadDocService.downLoadDoc(downLoadDocPara).subscribe(
     res => {
       console.log(res);
       if (res.code !== 0) {
         this.mes.warning(res.msg);
         return;
       } else {
         this.mes.success(res.msg);
       }
     },
     err => {
       console.log(err);
     }
   );
  }
}
