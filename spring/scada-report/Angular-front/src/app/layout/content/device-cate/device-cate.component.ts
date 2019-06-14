import { Component, OnInit } from '@angular/core';
import {trigger, state, transition, animate, style} from '@angular/animations';
import {GetDeviceCateService} from '../../../service/getDeviceCate/get-device-cate.service';
import {NzMessageService} from 'ng-zorro-antd';
import {GetTypeTreeService} from '../../../service/getTypeTree/get-type-tree.service';

@Component({
  selector: 'app-device-cate',
  templateUrl: './device-cate.component.html',
  styleUrls: ['./device-cate.component.scss'],
  animations: [
    trigger('collapseMotion', [
      state('expanded', style({ height: '*' })),
      // state('collapsed', style({ height: 0, overflow: 'hidden' })),
      // state('hidden', style({ height: 0, display: 'none' })),
      state('hidden', style({ height: 0, overflow: 'hidden' })),
      // transition('expanded => collapsed', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('expanded => hidden', animate(`100ms ease-out`)),
      // transition('collapsed => expanded', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('hidden => expanded', animate(`100ms ease-in`))
    ])
  ]
})
export class DeviceCateComponent implements OnInit {
  isShowTool = false;
  value;
  selectedValue = '正常';
  mapOfExpandData: { [ key: string ]: boolean } = {};
  listOfData = [
    ];
  constructor(
    private getDeviceCateService: GetDeviceCateService,
    private mes: NzMessageService,
    private getTypeTreeService: GetTypeTreeService
  ) { }

  ngOnInit() {
    this.getTypeList();
  }
   // getDeviceCate() {
   //  this.getDeviceCateService.getDeviceCate().subscribe(
   //    res => {
   //      console.log(res);
   //      this.listOfData = res.data.types.map(item => {
   //        return {
   //          id         : 1,
   //          name       : '注塑机66',
   //          fullName        : '注塑机',
   //          expand     : false,
   //          number    : 40,
   //          area: '类别',
   //          info: 'name',
   //          state: '正常',
   //          updateTime    : '2019-03-15 09:56',
   //          groups: [],
   //        };
   //      });
   //    },
   //    err => {
   //      console.log(err);
   //    }
   //  );
   // }
  getTypeList () {
    this.getTypeTreeService.getTypeTree().subscribe(
      res => {
        console.log(res);
             this.listOfData = res.data.map(item => {
               return {
                 id         : item.type.id,
                 name       : item.type.typeName,
                 fullName        : item.type.typeFullName,
                 expand     : false,
                 number    : item.type.sortNum,
                 area: item.type.catalogueType,
                 info: item.type.remark,
                 state: item.type.status,
                 updateTime    : item.type.updateTime,
                 groups: item.brands,
               };
             });
             },
      err => {
        console.log(err);
      }
    );
  }
}
