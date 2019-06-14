import { Component, OnInit } from '@angular/core';
import {GetDocListService} from '../../../../service/getDocList/get-doc-list.service';
import {DeleteDocService} from '../../../../service/deleteDoc/delete-doc.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-file-home',
  templateUrl: './file-home.component.html',
  styleUrls: ['./file-home.component.scss']
})
export class FileHomeComponent implements OnInit {
  listOfData = [
    // {
    //   id: '1',
    //   name: 'xxx使用说明文档',
    //   link: 32,
    //   router: '/ww/dsd/wdsdf/sdf2123/dsf',
    //   editor: 'Cop4',
    //   time: '2019-04-23 15:32',
    //   index: 0
    // },
    // {
    //   id: '2',
    //   name: 'xxx使用说明文档',
    //   link: 42,
    //   router: '/ww/dsd/wdsdf/sdf2123/dsf',
    //   editor: 'Cop4',
    //   time: '2019-04-23 15:32',
    //   index: 1
    // },
    // {
    //   id: '3',
    //   name: 'xxx使用说明文档',
    //   link: 32,
    //   router: '/ww/dsd/wdsdf/sdf2123/dsf',
    //   editor: 'Cop4',
    //   time: '2019-04-23 15:32',
    //   index: 2
    // }
  ];
  constructor(
    private getDocListService: GetDocListService,
    private deleteDocService: DeleteDocService,
    private mes: NzMessageService
  ) { }

  ngOnInit() {
    this.getDocList();
  }
getDocList() {
    this.getDocListService.getDocList().subscribe(
      res => {
        console.log(res);
        this.listOfData = res.data.map((item, index) => {
          return {
            author: item.author,
            classify: item.classify,
            createTime: item.createTime,
            docName: item.docName,
            downloadNum: item.downloadNum,
            id: item.id,
            introduction: item.introduction,
            label: item.label,
            type: item.type,
            index,
          };
        });
      },
      err => {
        console.log(err);
      }
    );
}
  deleteDoc(e) {
    const id = e.currentTarget.getAttribute('data-id');
    const deleteDocPara = {
      docId: id
    };
    this.deleteDocService.deleteDoc(deleteDocPara).subscribe(
      res => {
        console.log(res);
        if (res.code !== 0) {
          this.mes.warning(res.msg , {nzDuration: 3000});
          return;
        } else {
          this.mes.success(res.msg);
        }
        this.getDocList();
      },
      err => {
        console.log(err);
      }
    );
  }
}
