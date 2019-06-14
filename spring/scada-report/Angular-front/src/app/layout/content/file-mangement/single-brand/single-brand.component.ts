import { Component, OnInit } from '@angular/core';
import {GetTypeTreeService} from '../../../../service/getTypeTree/get-type-tree.service';
import {GetSingleBrandService} from '../../../../service/getSingleBrand/get-single-brand.service';
import {PassBrandIdService} from '../../../../service/passBrandId/pass-brand-id.service';
import {DeleteBrandService} from '../../../../service/deleteBrand/delete-brand.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';

@Component({
  selector: 'app-single-brand',
  templateUrl: './single-brand.component.html',
  styleUrls: ['./single-brand.component.scss']
})
export class SingleBrandComponent implements OnInit {
   currentBrandName;
   currentBrandId;
   typeName;
  createDate;
  docNum;
  faultCodeNum;
  sortNum;
  status;
  updateDate;
  currentTypeId;
  constructor(
    private getTypeTreeService: GetTypeTreeService,
    private getSingleBrandService: GetSingleBrandService,
    private passBrandIdService: PassBrandIdService,
    private deleteBrandService: DeleteBrandService,
    private mes: NzMessageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.passBrandIdService.passBrandId.subscribe(
      res => {
        console.log(res);
        this.currentTypeId = res['typeId'];
        const getSingleBrandPara  = {
          brandId: res['brandId']
        };
        this.currentBrandName = res['brandName'];
        this.currentBrandId = res['brandId'];
        this.getSingleBrand(getSingleBrandPara);
      }
    );
    this.refreshPage();
  }
  getSingleBrand(para) {
    this.getSingleBrandService.getSingleBrand(para).subscribe(
      result => {
        console.log(result);
        this.typeName = result.data.typeName;
        this.createDate = result.data.createDate;
        this.docNum = result.data.docNum;
        this.faultCodeNum = result.data.faultCodeNum;
        this.sortNum = result.data.sortNum;
        this.updateDate = result.data.updateDate;
        this.status = result.data.status;
      },
      err => {
        console.log(err);
      }
    );
  }
  deleteBrand(e) {
    const id = e.currentTarget.getAttribute('brandId');
    console.log(id);
    const deleteBrandPara = {
      id
    };
    this.deleteBrandService.deleteBrand(deleteBrandPara).subscribe(
      res => {
        console.log(res);
        if (res.code === 1) {
          this.mes.warning(res.msg);
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.refreshPage();
      },
      err => {
        console.log(err);
      }
    );
  }
  refreshPage() {
    this.getTypeTreeService.getTypeTree().subscribe(
      res => {
        console.log(res);
        if (res.data.length === 0) {
          this.router.navigate(['/layout/addCate']);
          return;
        }
        const tempArray = res.data.filter(item => {
          return item.brands.length !== 0;
        });
        console.log(tempArray);
        this.currentBrandId = tempArray[0].brands[0].id;
        this.currentBrandName = tempArray[0].brands[0].brandName;
        const getSingleBrandPara  = {
          brandId: this.currentBrandId
        };
        this.getSingleBrand(getSingleBrandPara);
      },
      err => {
        console.log(err);
      }
    );
  }
}
