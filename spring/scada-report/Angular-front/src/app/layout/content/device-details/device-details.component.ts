import { Component, OnInit, OnDestroy, AfterViewInit  } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {Router} from '@angular/router';
import {ShowArchivesOperationService} from '../../../service/showArchivesOperation/show-archives-operation.service';
import {GetDeviceInfohomeService} from '../../../service/getDeviceInfohome/get-device-infohome.service';
import {GetDeviceListService} from '../../../service/getDeviceList/get-device-list.service';

@Component({
  selector: 'app-device-details',
  templateUrl: './device-details.component.html',
  styleUrls: ['./device-details.component.scss'],
  animations: [
    trigger('collapseMotion', [
      state('expanded', style({ height: '*' })),
      // state('collapsed', style({ height: 0, overflow: 'hidden' })),
      // state('hidden', style({ height: 0, display: 'none' })),
      state('hidden', style({ height: 0, overflow: 'hidden' })),
      // transition('expanded => collapsed', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('expanded => hidden', animate(`300ms ease-out`)),
      // transition('collapsed => expanded', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('hidden => expanded', animate(`300ms ease-in`))
    ])
  ]
})
export class DeviceDetailsComponent implements OnInit , OnDestroy, AfterViewInit {
  serviceList = [];
  showOperationSub;
  isShowPageList = true;
  currentDevicecate;
  currentDeviceName;
  currentDeviceNum;
  source = [
    // {
    //   name: '注塑机',
    //   count: 5,
    //   isOpen: false,
    //   index: 0,
    //   children: [
    //     {
    //       service_name: '水压测试仪01'
    //     },
    //     {
    //       service_name: '水压测试仪01'
    //     },
    //     {
    //       service_name: '水压测试仪01'
    //     },
    //     {
    //       service_name: '水压测试仪01'
    //     }
    //   ],
    // },
    // {
    //   name: '一体机',
    //   count: 20,
    //   isOpen: false,
    //   index: 1,
    //   children: [
    //     {
    //       service_name: '水压测试仪01'
    //     },
    //     {
    //       service_name: '水压测试仪02'
    //     },
    //     {
    //       service_name: '水压测试仪03'
    //     },
    //     {
    //       service_name: '水压测试仪04'
    //     }
    //   ],
    // }
  ];
  constructor(
    private router: Router,
    private showArchivesOperationService: ShowArchivesOperationService,
    private getDeviceInfohomeService: GetDeviceInfohomeService,
    private getDeviceListService: GetDeviceListService
  ) { }

  ngOnInit() {
    this.getDeviceListService.getDeviceList().subscribe(
      res => {
        console.log(res);
        const equipArray = Object.keys(res.data);
        console.log(equipArray);
        this.source = equipArray.map((item, index) => {
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
        this.currentDevicecate = this.source[0].name;
        this.currentDeviceName = this.source[0].children[0].service_name;
        this.currentDeviceNum = this.source[0].children[0].deviceNum;
      },
      err => {
        console.log(err);
      }
    );
  }
  ngAfterViewInit(): void {
    this.showOperationSub =  this.showArchivesOperationService.showArchivesOperation.subscribe(
      res => {
        console.log('监听到了');
        console.log(res);
        this.isShowPageList = res;
      }
    );
  }

  goDetails(e) {
    console.log(e);
    const deviceNum = e.currentTarget.getAttribute('data-deviceNum');
    this.currentDeviceNum = deviceNum;
    this.currentDevicecate = e.currentTarget.getAttribute('data-cate');
    this.currentDeviceName = e.currentTarget.getAttribute('data-name');
  }
  switch(e) {
    console.log(e.currentTarget.getAttribute('data-index'));
    const index = e.currentTarget.getAttribute('data-index');
    this.source[index].isOpen = !this.source[index].isOpen;
    // e.stopPropagation();
  }
  addDirective(directive) {
    this.serviceList.push(directive);
  }
  ngOnDestroy(): void {
    // this.showOperationSub.unsubscribe();
  }
}
