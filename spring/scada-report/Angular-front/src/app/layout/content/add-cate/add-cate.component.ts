import {Component, OnInit} from '@angular/core';
import {AddTypeService} from '../../../service/addType/add-type.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-cate',
  templateUrl: './add-cate.component.html',
  styleUrls: ['./add-cate.component.scss']
})
export class AddCateComponent implements OnInit {
  value = 'http://10.168.4.220:8081';
  ways = '自定义添加';
  deviceType;
  deviceTypeFullName;
  orderNum;
  belongType = '目录';
  status = '启用';
  textValue;

  constructor(
    private addTypeService: AddTypeService,
    private mes: NzMessageService,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  submit() {
    if (!this.deviceType || !this.deviceTypeFullName || !this.orderNum || !this.belongType || !this.status) {
      this.mes.warning('还有未填项！');
      return;
    }
    const addTypePara = {
      addWay: this.ways,
      typeName: this.deviceType,
      typeFullName: this.deviceTypeFullName,
      sortNum: this.orderNum,
      catalogueType: this.belongType,
      status: this.status,
      remark: this.textValue,
    };
    this.addTypeService.addType(addTypePara).subscribe(
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
