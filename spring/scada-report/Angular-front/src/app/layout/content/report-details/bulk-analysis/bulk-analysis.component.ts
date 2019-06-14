import { Component, OnInit } from '@angular/core';
import {GetTableBodyService} from '../../../../service/getTableBody/get-table-body.service';
import {GetTableHeaderService} from '../../../../service/getTableHeader/get-table-header.service';
import {GetTableListService} from '../../../../service/getTableList/get-table-list.service';
import {PassReportIdService} from '../../../../service/passReportId/pass-report-id.service';
import {PassDeleteTableInfoService} from '../../../../service/passDeleteTableInfo/pass-delete-table-info.service';
import {UpdateTableParamService} from '../../../../service/updateTableParam/update-table-param.service';
import { NzFormatEmitEvent, NzTreeComponent, NzTreeNodeOptions } from 'ng-zorro-antd';
import {ViewChild} from '@angular/core';
import {GetDataTypeTreeService} from '../../../../service/getDataTypeTree/get-data-type-tree.service';

@Component({
  selector: 'app-bulk-analysis',
  templateUrl: './bulk-analysis.component.html',
  styleUrls: ['./bulk-analysis.component.scss']
})
export class BulkAnalysisComponent implements OnInit {
  @ViewChild('nzTreeComponent') nzTreeComponent: NzTreeComponent;
  selectedValue;
  paramName;
  isUse;
  keyValue;
  paraType;
  isVisible = false;
  currentTableId;
  value;
  tableHeaderList = [];
  dataSet = [
    ];
  nodes = [];
  fixedValue;
  searchSource = [];
  constructor(
    private getTableHeaderService: GetTableHeaderService,
    private getTableBodyService: GetTableBodyService,
    private getTableListService: GetTableListService,
    private passReportIdService: PassReportIdService,
    private passDeleteTableInfoService: PassDeleteTableInfoService,
    private getDataTypeTreeService: GetDataTypeTreeService
  ) { }
  checkOptions = [
    { label: '运行', value: 'run' },
    { label: '待机', value: 'off', checked: true },
    { label: '报警', value: 'warning' }
  ];
  ngOnInit() {
    this.passDeleteTableInfoService.passDeleteTableInfo.subscribe(
      result => {
        this.getTableListService.getTableList().subscribe(
          res => {
            console.log(res);
            this.currentTableId = res.data[0].id;
            const getTableHeaderPara = {
              id: this.currentTableId
            };
            this.getTableHeader(getTableHeaderPara);
          },
          err => {
            console.log(err);
          }
        );
      }
    );
    this.getTableListService.getTableList().subscribe(
      res => {
        console.log(res);
        this.currentTableId = res.data[0].id;
        const getTableHeaderPara = {
          id: this.currentTableId
        };
        this.getTableHeader(getTableHeaderPara);
        },
      err => {
        console.log(err);
      }
    );
    this.passReportIdService.passReportId.subscribe(
      res => {
        console.log(res);
        const id = res;
        this.currentTableId = id;
        const getTableHeaderPara = {
          id: res
        };
        this.getTableHeader(getTableHeaderPara);
      },
      err => {
        console.log(err);
      }
      );
    // 树组件数据
    this.getDataTypeTreeService.getDataTypeTree('').subscribe(
      res => {
        console.log(res);
        const temp = Object.keys(res.data);
        console.log(temp);
        this.nodes = temp.map((item, index) => {
          return {
            title: item,
            key: `${index}`,
            disabled: true,
            checked: false,
            children: res.data[item].map((child, index0) => {
              return {
                title: child,
                key: `${index}-${index0}`,
                isLeaf: true
              };
            })
          };
        });
      },
      err => {
        console.log(err);
      }
    );
  }
  log(value): void {
    console.log(value);
  }
  getTableHeader(para) {
    this.getTableHeaderService.getTableHeader(para).subscribe(
      result => {
        console.log(result);
        this.searchSource = result.data.paramList;
        this.tableHeaderList = result.data.paramList.map(item => {
          return item.paramName;
        });
        console.log(this.tableHeaderList);
        const getTableBodyPara = {
          id: this.currentTableId
        };
        this.getTableBody(getTableBodyPara);
      },
      error => {
        console.log(error);
      }
    );
  }
  getTableBody(para) {
    this.getTableBodyService.getTableBody(para).subscribe(
      result => {
        console.log(result);
        if (this.tableHeaderList.length === 0) {
          this.dataSet = [];
          return;
        }
        // 先得到设备数量，以表头第一个参数值里设备数为准
        console.log(result.data[this.tableHeaderList[0]].data);
        console.log(this.tableHeaderList[0]);
        this.dataSet = Object.keys(result.data[this.tableHeaderList[0]].data).map(item => {
          return {
            deviceNum: item,
            tdValue: []
          };
        });
        console.log('dataset', this.dataSet);
        this.tableHeaderList.forEach((item, index) => {
        console.log(result.data[item])  ;
        console.log(result.data[item].data);
        const tempArray = Object.values(result.data[item].data);
        console.log(tempArray);
        tempArray.forEach((item0 , index0 ) => {
          console.log(this.dataSet[index0]);
          if (this.dataSet[index0]) {
            this.dataSet[index0].tdValue.push(item0);
          }
        } );
        });
        console.log('now', this.dataSet);
      },
      error => {
        console.log(error);
      }
    );
  }
  // 删除修改表头
  editPara(e) {
    this.isVisible = true;
  }
  deletePara(e) {}
  handleOk(): void {
    this.isVisible = false;
  }

  handleCancel(): void {
    this.isVisible = false;
  }
  // 树组件
  nzClick(event: NzFormatEmitEvent): void {
    console.log(event);
  }

  nzCheck(event: NzFormatEmitEvent): void {
    console.log(event);
  }

  // nzSelectedKeys change
  nzSelect(keys: string[]): void {
    console.log(keys, this.nzTreeComponent.getSelectedNodeList());
  }
}
