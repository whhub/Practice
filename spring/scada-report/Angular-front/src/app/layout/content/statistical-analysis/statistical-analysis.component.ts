import {Component, OnInit} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {GetTableListService} from '../../../service/getTableList/get-table-list.service';
import {AddTableService} from '../../../service/addTable/add-table.service';
import {NzMessageService} from 'ng-zorro-antd';
import {GetSingleTableService} from '../../../service/getSingleTable/get-single-table.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-statistical-analysis',
  templateUrl: './statistical-analysis.component.html',
  styleUrls: ['./statistical-analysis.component.scss'],
  animations: [
    trigger('collapseMotion', [
      state('expanded', style({ height: '*', padding: '10px 0' })),
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
export class StatisticalAnalysisComponent implements OnInit {
  textValue;
  value;
  tableName;
  source = [];
  isShowTool = false;
  isVisible = false;
  constructor(
    private getTableListService: GetTableListService,
    private addTableService: AddTableService,
    private mes: NzMessageService,
    private getSingleTableService: GetSingleTableService,
    private router: Router
    ) {
  }

  ngOnInit() {
    this.getTableList();
  }
  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    if (!this.tableName) {
      this.mes.warning('报表名称为必填项！');
      return;
    }
    const addTablePara = {
      tableName: this.tableName,
      remarks: this.textValue
    };
    this.addTableService.addTable(addTablePara).subscribe(
      res => {
        console.log(res);
        if (res.code !== 0) {
          this.mes.warning(res.msg);
        } else {
          this.mes.success(res.msg);
          this.getTableList();
        }
      },
      err => {
        console.log(err);
      }
    );
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }
  getTableList() {
    this.getTableListService.getTableList().subscribe(
      res => {
        console.log(res);
        this.source = res.data.map(item => {
          return {
            createTime: item.createTime,
            id: item.id,
            remarks: item.remarks,
            tableName: item.tableName,
            dataNum: item.dataNum
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }
  details(e) {
    // console.log(e);
    const id = e.currentTarget.getAttribute('data-id');
    const  getSingleTablePara = {
      id
    };
    this.getSingleTableService.getSingleTable(getSingleTablePara).subscribe(
      res => {
        console.log(res);
        const keyArray = Object.keys(res.data);
        // console.log(keyArray);
        // data对象遍历数组为空，说明表里没内容，跳转添加内容页面
        if (keyArray.length === 0) {
          this.router.navigate(['/layout/reportDetails/create']);
        } else {
          this.router.navigate(['/layout/reportDetails/create']);
        }
      },
      err => {
        console.log(err);
      }
    );
  }
}

