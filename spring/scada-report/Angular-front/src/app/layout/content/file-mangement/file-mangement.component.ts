import { Component, OnInit } from '@angular/core';
import {GetDocDeviceTypeService} from '../../../service/getDocDeviceType/get-doc-device-type.service';
import {GetBrandByCateService} from '../../../service/getBrandByCate/get-brand-by-cate.service';
import {Router} from '@angular/router';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {GetTypeTreeService} from '../../../service/getTypeTree/get-type-tree.service';
import {PassBrandIdService} from '../../../service/passBrandId/pass-brand-id.service';

@Component({
  selector: 'app-file-mangement',
  templateUrl: './file-mangement.component.html',
  styleUrls: ['./file-mangement.component.scss'],
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
export class FileMangementComponent implements OnInit {
  siderSource = [];
  brandCount = 0;
  isDropBoxOpen = false;
  currentBrandName;
  currentBrandId;
  constructor(
    private getDocDeviceTypeService: GetDocDeviceTypeService,
    private getBrandByCateService: GetBrandByCateService,
    private router: Router,
    private getTypeTreeService: GetTypeTreeService,
    private passBrandIdService: PassBrandIdService
  ) { }

  ngOnInit() {
    // this.getDocDeviceTypeService.getDocDeviceType().subscribe(
    //   res => {
    //     console.log(res);
    //     this.siderSource = res.data.map(item => {
    //       return {
    //         typeName: item.typeName,
    //         id: item.id,
    //         children : []
    //       };
    //     });
    //     console.log(this.siderSource);
    //     // this.siderSource.forEach(item => {
    //     //   const getBrandByCatePara = {
    //     //     typeId: item.id
    //     //   };
    //     //   this.getBrandByCateService.getBrandByCate(getBrandByCatePara).subscribe(
    //     //     result => {
    //     //       console.log(result);
    //     //       this.brandCount = this.brandCount + result.data.length;
    //     //       if (result.data.length > 0) {
    //     //         const typeId = result.data[0].typeId;
    //     //         this.siderSource.forEach(item0 => {
    //     //           if (item0.id === typeId) {
    //     //             item0.children = result.data;
    //     //           }
    //     //         });
    //     //       }
    //     //       console.log(this.siderSource);
    //     //       },
    //     //     error => {
    //     //       console.log(error);
    //     //     }
    //     //   );
    //     // });
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
    this.getTypeTreeService.getTypeTree().subscribe(
      res => {
        console.log(res);
        this.siderSource = res.data.map(item => {
            return {
                typeName: item.type.typeName,
                id: item.type.id,
                children : item.brands
          };
        });
        console.log(this.siderSource);
        this.siderSource.forEach(item => {
          this.brandCount += item.children.length;
        });
        if (res.data.length === 0 || !this.brandCount) {
          console.log('添加页');
          console.log(res.data);
          console.log(this.brandCount);
          this.router.navigate(['/layout/fileMangement/addType']);
        } else {
          console.log('品牌详情ye');
          this.router.navigate(['/layout/fileMangement/singleBrand']);
        }
      },
      err => {
        console.log(err);
      }
    );
  }
  goSingleBrandDetails(e) {
    console.log(e);
    this.currentBrandName = e.currentTarget.getAttribute('brandName');
    this.currentBrandId = e.currentTarget.getAttribute('brandId');
    const typeId = e.currentTarget.getAttribute('typeId');
    this.router.navigate(['/layout/fileMangement/singleBrand']);
    this.passBrandIdService.passBrandId.next({
      brandName: this.currentBrandName,
      brandId: this.currentBrandId,
      typeId
    });
  }
}
