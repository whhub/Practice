import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GetErrorCodeByBrandIdService} from '../../../../service/getErrorCodeByBrandId/get-error-code-by-brand-id.service';
import {NzMessageService} from 'ng-zorro-antd';
import {UpdateFaultCodeService} from '../../../../service/updateFaultCode/update-fault-code.service';
import {AddErrorCodeService} from '../../../../service/addErrorCode/add-error-code.service';
import {DeleteErrorCodeService} from '../../../../service/deleteErrorCode/delete-error-code.service';

@Component({
  selector: 'app-error-code',
  templateUrl: './error-code.component.html',
  styleUrls: ['./error-code.component.scss']
})
export class ErrorCodeComponent implements OnInit {
  value;
  selectedValue;
  currentBrandId;
  currentTypeId;
  listOfData = [
    {
      index: 0,
      errorCode: '',
      isUse: String(false),
      errMarks: '',
      description: '',
      editAble: false,
      typeId: this.currentTypeId,
      brandId: this.currentBrandId,
    }
  ];

  constructor(
    private activatedRoute: ActivatedRoute,
    private getErrorCodeByBrandIdService: GetErrorCodeByBrandIdService,
    private mes: NzMessageService,
    private updateFaultCodeService: UpdateFaultCodeService,
    private addErrorCodeService: AddErrorCodeService,
    private deleteErrorCodeService: DeleteErrorCodeService
  ) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      res => {
        console.log(res);
        this.currentBrandId = res.brandId;
        this.currentTypeId = res.typeId;
      this.getErrorCodeByBrandId();
      }
    );
  }

  addErrorCode() {
    if (this.listOfData.length !== 0) {
      console.log('123');
      const lastItem = this.listOfData[this.listOfData.length - 1];
      if (!lastItem.errorCode || !lastItem.errMarks) {
        this.mes.warning('前一项还未填完，无法新增！');
        return;
      }
    }
    this.listOfData = [...this.listOfData, {
      index: this.listOfData.length,
      errorCode: '',
      isUse: String(false),
      errMarks: '',
      description: '',
      editAble: true,
      typeId: this.currentTypeId,
      brandId: this.currentBrandId,
    }];
  }

  submit(e) {
    console.log(e);
    const id = e.currentTarget.getAttribute('data-id');
    const index = e.currentTarget.getAttribute('data-index');
    if (!this.listOfData[index].errorCode || !this.listOfData[index].errMarks) {
      this.mes.warning('有必填项未填！');
      return;
    }
    if (id) {
      const item = this.listOfData[index];
      const updateFaultCode = {
        id,
        typeId: this.currentTypeId,
        brandId: this.currentBrandId,
        code: this.listOfData[index].errorCode,
        isUse: Boolean(this.listOfData[index].isUse) ,
        faultInfo: this.listOfData[index].errMarks,
        remark: this.listOfData[index].description
      };
      this.updateFaultCodeService.updateFaultCode(updateFaultCode).subscribe(
        res => {
          console.log(res);
          if (res.code === 1) {
            this.mes.warning(res.msg);
            return;
          } else {
            this.mes.success(res.msg);
          }
          this.getErrorCodeByBrandId();
        },
        err => {
          console.log(err);
        }
      );
    } else {
      // id 不存在，即为调添加接口
      const addErrorCodePara = {
        typeId: this.currentTypeId,
        brandId: this.currentBrandId,
        code: this.listOfData[index].errorCode,
        isUse: Boolean(this.listOfData[index].isUse) ,
        faultInfo: this.listOfData[index].errMarks,
        remark: this.listOfData[index].description
      };
      this.addErrorCodeService.addErrorCode(addErrorCodePara).subscribe(
        res => {
          console.log(res);
          if (res.code === 1) {
            this.mes.warning(res.msg);
            return;
          } else {
            this.mes.success(res.msg);
          }
          this.getErrorCodeByBrandId();
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  cancel(e) {
    console.log(e);
    const id = e.currentTarget.getAttribute('data-id');
    const index = e.currentTarget.getAttribute('data-index');
    console.log(id);
    console.log(index);
    if (id) {
      this.listOfData[index].editAble = false;
    } else {
      this.listOfData = this.listOfData.filter(item => {
        return item.index !== Number(index);
      });
      console.log(this.listOfData);
    }
  }
  getErrorCodeByBrandId() {
    const getErrorCodeByBrandIdPara = {
      brandId: this.currentBrandId
    };
    this.getErrorCodeByBrandIdService.getErrorCodeByBrandId(getErrorCodeByBrandIdPara).subscribe(
      result => {
        console.log(result);
        this.listOfData = result.data.map((item, index) => {
          return {
            index,
            errorCode: item.code,
            isUse: String(item.isUse),
            errMarks: item.faultInfo,
            description: item.remark,
            editAble: false,
            id: item.id,
            typeId: item.typeId,
            brandId: item.brandId
          };
        });
        },
      error => {
        console.log(error);
      }
    );
  }
  deleteErrorCode(e) {
    const id = e.currentTarget.getAttribute('data-id');
    const deleteErrorCodePara = {
      id
    };
    this.deleteErrorCodeService.deleteErrorCode(deleteErrorCodePara).subscribe(
      res => {
        console.log(res);
        if (res.code === 1) {
          this.mes.warning(res.msg);
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.getErrorCodeByBrandId();
      },
      err => {
        console.log(err);
      }
    );
  }
}
