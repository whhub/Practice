import { Component, OnInit } from '@angular/core';
import {AddTypeService} from '../../../service/addType/add-type.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';
import {GetTypeListService} from '../../../service/getTypeList/get-type-list.service';
import {UpdateCateService} from '../../../service/updateCate/update-cate.service';

@Component({
  selector: 'app-update-cate',
  templateUrl: './update-cate.component.html',
  styleUrls: ['./update-cate.component.scss']
})
export class UpdateCateComponent implements OnInit {

  value = 'http://10.168.4.220:8081';
  ways = '自定义添加';
  deviceType;
  deviceTypeFullName;
  orderNum;
  belongType = '目录';
  status = '正常';
  textValue;
  source = [];
  currentItem;
  constructor(
    private addTypeService: AddTypeService,
    private mes: NzMessageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private getTypeListService: GetTypeListService,
    private updateCateService: UpdateCateService
  ) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      res => {
        console.log(res);
        const id = res.id;
        this.getTypeListService.getTypeList().subscribe(
          result => {
            console.log(result);
            const array = result.data.filter(item => {
              return item.id === Number(id);
            });
            this.currentItem = array[0];
            console.log(this.currentItem);
            this.deviceType = this.currentItem.typeName;
            this.deviceTypeFullName = this.currentItem.typeFullName;
            this.orderNum = this.currentItem.sortNum;
            this.status = this.currentItem.status;
            this.textValue = this.currentItem.remark;
          },
          error => {
            console.log(error);
          }
        );
      }
    );
  }

  submit() {
    if (!this.deviceType || !this.deviceTypeFullName || !this.orderNum || !this.belongType || !this.status) {
      this.mes.warning('还有未填项！');
      return;
    }
    const updateTypePara = {
      addWay: this.ways,
      typeName: this.deviceType,
      typeFullName: this.deviceTypeFullName,
      sortNum: this.orderNum,
      catalogueType: this.belongType,
      status: this.status,
      remark: this.textValue,
    };
    this.updateCateService.updateCate(updateTypePara).subscribe(
      res => {
        console.log(res);
        if (res.code !== 0) {
          this.mes.warning(res.msg);
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.router.navigate(['/layout/fileMangement/home']);
      },
      err => {
        console.log(err);
      }
    );
  }
}
