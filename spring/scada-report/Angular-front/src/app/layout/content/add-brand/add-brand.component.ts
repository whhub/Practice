import {Component, OnInit} from '@angular/core';
import {GetTypeListService} from '../../../service/getTypeList/get-type-list.service';
import {AddBrandService} from '../../../service/addBrand/add-brand.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.scss']
})
export class AddBrandComponent implements OnInit {
  value = 'http://10.168.4.220:8081';
  brandName;
  brandFullName;
  orderNum;
  selectSource = [];
  status = '禁用';
  ways = 'local';
  types = '品牌';
  textValue;
  isVisible = false;
  cate;
  isSelectDisable = false;

  constructor(
    private getTypeListService: GetTypeListService,
    private addBrandService: AddBrandService,
    private mes: NzMessageService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      res => {
        console.log(res);
        if (res.id) {
          console.log(res.id);
          console.log(typeof res.id);
          this.cate = res.id;
          this.isSelectDisable = true;
        } else {
          return;
        }
      }
    );
    this.getTypeListService.getTypeList().subscribe(
      res => {
        console.log(res);
        this.selectSource = res.data.map(item => {
          return {
            value: item.id,
            label: item.typeName
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }

  submit() {
    if (!this.cate || !this.brandName || !this.status || !this.types) {
      this.mes.warning('还有必填项未填');
      return;
    }
    const addBrandPara = {
      typeId: Number(this.cate),
      brandName: this.brandName,
      brandFullName: this.brandFullName,
      sortNum: this.orderNum,
      catalogueType: this.types,
      status: this.status,
      remark: this.textValue
    };
    this.addBrandService.addBrand(addBrandPara).subscribe(
      res => {
        console.log(res);
        if (res.code === 1) {
          this.mes.warning(res.msg);
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.router.navigate(['/layout/fileMangement']);
      },
      err => {
        console.log(err);
      }
    );
  }
}
