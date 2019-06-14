import { Component, OnInit } from '@angular/core';
import {UploadFileService} from '../../../service/uploadFile/upload-file.service';
import {GetDeviceCateService} from '../../../service/getDeviceCate/get-device-cate.service';
import {GetBrandListService} from '../../../service/getBrandList/get-brand-list.service';
import {NzMessageService} from 'ng-zorro-antd';
import {GetTypeTreeService} from '../../../service/getTypeTree/get-type-tree.service';
import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';
import {GetSingleFileService} from '../../../service/getSingleFile/get-single-file.service';
import {EditFileService} from '../../../service/editFile/edit-file.service';

@Component({
  selector: 'app-file-edit',
  templateUrl: './file-edit.component.html',
  styleUrls: ['./file-edit.component.scss']
})
export class FileEditComponent implements OnInit {
  value;
  fileType;
  fileClass ;
  textValue;
  fileName;
  currentDocId;
  currentFile: File;
  downLoadCount;
  createTime;
  selectSource = [];
  brandSource = [];
  constructor(
    private uploadFileService: UploadFileService,
    private getDeviceCateService: GetDeviceCateService,
    private getBrandListService: GetBrandListService,
    private mes: NzMessageService,
    private getTypeTreeService: GetTypeTreeService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private getSingleFileService: GetSingleFileService,
    private editFileService: EditFileService
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      res => {
        console.log(res);
        const id = res.id;
        this.getTypeTreeService.getTypeTree().subscribe(
          res0 => {
            console.log(res0);
            this.selectSource = res0.data.map(item => {
              return {
                label: item.type.typeName,
                value: item.type.typeName,
                brands: item.brands
              };
            });
            const getSingleFilePara = {
              id
            };
            this.getSingleFileService.getSingleFile(getSingleFilePara).subscribe(
              result => {
                console.log(result);
                this.currentDocId = result.data.id;
                this.fileName = result.data.docName;
                this.fileType = result.data.type;
                this.value = result.data.label;
                this.textValue = result.data.introduction;
                this.downLoadCount = result.data.downloadNum;
                this.createTime = result.data.createTime;
                const brandSource = this.selectSource.filter(item => {
                  return item.label === this.fileType;
                });
                this.brandSource = brandSource[0].brands.map(item => {
                  return {
                    label: item.brandFullName,
                    value: item.brandFullName
                  };
                });
                console.log(this.brandSource);
                console.log(result.data.classify);
                this.fileClass = result.data.classify;   // 赋值不了
                // this.fileClass = {label: 'A品牌', value: 'A品牌'};   // 赋值不了
                // this.fileClass =  'A品牌';   // 赋值不了
                },
              error => {
                console.log(error);
              }
            );
            },
          err => {
            console.log(err);
          }
        );
        },
      error => {
        console.log(error);
      }
    );
    this.getTypeTree();
  }
  fileTypeChange(e) {
    console.log(e);
    console.log(this.selectSource);
    const brandSource = this.selectSource.filter(item => {
      return item.value === this.fileType;
    });
    this.brandSource = brandSource[0].brands.map(item => {
      return {
        label: item.brandFullName,
        value: item.ibrandFullNamed
      };
    });
    // console.log(this.brandSource);
  }
  edit() {
    if (!this.fileName || !this.fileType || !this.fileClass ) {
      this.mes.warning('有必填项未填写！');
      return;
    }
    const reg = /\./;
    const isHasPoint = reg.test(this.fileName);
    console.log(isHasPoint);
    if (isHasPoint) {
      this.mes.warning('文件名不能含有 . ');
      return;
    }
    const editFilePara = {
      id: this.currentDocId,
      docName: this.fileName,
      type: this.fileType,
      classify: this.fileClass,
      createTime: this.createTime,
      downloadNum: this.downLoadCount,
      label: this.value,
      author: '',
      introduction: this.textValue
    };
    this.editFileService.editFile(editFilePara).subscribe(
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
  getTypeTree() {
    this.getTypeTreeService.getTypeTree().subscribe(
      res => {
        console.log(res);
        this.selectSource = res.data.map(item => {
          return {
            label: item.type.typeName,
            value: item.type.typeName,
            brands: item.brands
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }

}
