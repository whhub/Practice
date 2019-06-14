import {Component, OnInit} from '@angular/core';
import {UploadFileService} from '../../../service/uploadFile/upload-file.service';
import {GetDeviceCateService} from '../../../service/getDeviceCate/get-device-cate.service';
import {GetBrandListService} from '../../../service/getBrandList/get-brand-list.service';
import {NzMessageService} from 'ng-zorro-antd';
import {GetTypeTreeService} from '../../../service/getTypeTree/get-type-tree.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {
  value;
  fileType;
  fileClass;
  textValue;
  fileName;
  currentFile: File;
  label;
  selectSource = [];
  brandSource = [];

  constructor(
    private uploadFileService: UploadFileService,
    private getDeviceCateService: GetDeviceCateService,
    private getBrandListService: GetBrandListService,
    private mes: NzMessageService,
    private getTypeTreeService: GetTypeTreeService,
    private router: Router
  ) {
  }

  ngOnInit() {
    // this.getDeviceCateService.getDeviceCate().subscribe(
    //   res => {
    //     console.log(res);
    //     this.selectSource = res.data.types.map(item => {
    //       return {
    //         label: item.typeName,
    //         value: item.id
    //       };
    //     });
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
    this.getTypeTreeService.getTypeTree().subscribe(
      res => {
        console.log(res);
        this.selectSource = res.data.map(item => {
          return {
            label: item.type.typeName,
            value: item.type.id,
            brands: item.brands
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }

  beforeUpload = (file: File) => {
    console.log(file);
    this.currentFile = file;
  };
  removeFile = (file: File) => {
    console.log(file);
    this.currentFile = null;
    return true;
  };

  fileTypeChange(e) {
    console.log(e);
    this.fileClass = null;
    console.log(this.selectSource);
    const brandSource = this.selectSource.filter(item => {
      return item.value === Number(e);
    });
    this.brandSource = brandSource[0].brands.map(item => {
      return {
        label: item.brandFullName,
        value: item.id
      };
    });
    console.log(this.brandSource);
    // const getBrandListPara = {
    //   typeId: e
    // };
    // this.getBrandListService.getBrandList(getBrandListPara).subscribe(
    //   res => {
    //     console.log(res);
    //     this.brandSource = res.data.map(item => {
    //       return {
    //         label: item.brandFullName,
    //         value: item.id
    //       };
    //     });
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
  }

  submit() {
    if (!this.fileName || !this.fileType || !this.fileClass || !this.currentFile) {
      this.mes.warning('有必填项未填写！');
      console.log(this.fileName);
      console.log(this.fileType);
      console.log(this.fileClass);
      console.log(this.currentFile);
      return;
    }
    const reg = /\./;
    const isHasPoint = reg.test(this.fileName);
    console.log(isHasPoint);
    if (isHasPoint) {
      this.mes.warning('文件名不能含有 . ');
      return;
    }
    // const uploadFilePara = {
    //   file: this.currentFile,
    //   docName: this.fileName,
    //   typeId: Number(this.fileType),
    //   brandId: Number(this.fileClass),
    //   label: this.label,
    //   introduction: this.textValue
    // };
    const uploadFilePara = new FormData();
    uploadFilePara.append('file', this.currentFile);
    uploadFilePara.append('docName', this.fileName);
    uploadFilePara.append('typeId', this.fileType);
    uploadFilePara.append('brandId', this.fileClass);
    uploadFilePara.append('label', this.label);
    uploadFilePara.append('introduction', this.textValue);
    console.log(uploadFilePara);
    this.uploadFileService.uploadFile(uploadFilePara).subscribe(
      res => {
        console.log(res);
        if (res.code !== 0) {
          this.mes.warning(res.msg);
        } else {
          this.mes.success(res.msg);
          this.router.navigate(['/layout/fileMangement/home']);
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  fileClassChange(e) {
    console.log(e);
  }
}
