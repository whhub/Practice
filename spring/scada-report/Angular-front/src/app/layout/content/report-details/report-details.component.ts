import {Component, OnInit} from '@angular/core';
import {GetTableListService} from '../../../service/getTableList/get-table-list.service';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd';
import {PassReportIdService} from '../../../service/passReportId/pass-report-id.service';
import {DeleteReportService} from '../../../service/deleteReport/delete-report.service';
import {PassDeleteTableInfoService} from '../../../service/passDeleteTableInfo/pass-delete-table-info.service';

@Component({
  selector: 'app-report-details',
  templateUrl: './report-details.component.html',
  styleUrls: ['./report-details.component.scss']
})
export class ReportDetailsComponent implements OnInit {
  currentItem = 0;
  value;
  dateFormat;
  currentId;
  currentName;
  currentTableId;
  source = [
    // {
    //   name: '总分析1',
    //   count: 5,
    //   isOpen: false,
    //   index: 0,
    //   url: 'bulk'
    // },
    // {
    //   name: '控制状态',
    //   count: 20,
    //   isOpen: false,
    //   index: 1,
    //   url: 'control'
    // },
    // {
    //   name: '生产状态',
    //   count: 20,
    //   isOpen: false,
    //   index: 2,
    //   url: 'produce'
    // },
    // {
    //   name: '设备状态时间',
    //   count: 20,
    //   isOpen: false,
    //   index: 3,
    //   url: 'deviceStatusTime'
    // },
    // {
    //   name: '运行时间图表',
    //   count: 20,
    //   isOpen: false,
    //   index: 4,
    //   url: 'runTimeChart'
    // }
  ];

  constructor(
    private getTableListService: GetTableListService,
    private router: Router,
    private mes: NzMessageService,
    private passReportIdService: PassReportIdService,
    private deleteReportService: DeleteReportService,
    private passDeleteTableInfoService: PassDeleteTableInfoService
  ) {
  }

  ngOnInit() {
    this.getTableList();
  }
  cancel(): void {
    this.mes.info('取消删除');
  }

  confirm(): void {
    this.mes.info('确认删除');
    const deleteReportPara = {
      id: this.currentTableId
    };
    this.deleteReportService.deleteReport(deleteReportPara).subscribe(
      res => {
        console.log(res);
        this.getTableList();
        this.passDeleteTableInfoService.passDeleteTableInfo.next('delete');
      },
      err => {
        console.log(err);
      }
    );
  }
  goDetails(e) {
    console.log(e);
    const index = e.currentTarget.getAttribute('data-index');
    const id = e.currentTarget.getAttribute('data-id');
    this.currentTableId = id;
    console.log(index);
    console.log(typeof index);
    this.currentItem = Number(index);
    this.passReportIdService.passReportId.next(id);
  }
  getTableList() {
    this.getTableListService.getTableList().subscribe(
      res => {
        console.log(res);
        if (res.data.length === 0) {
          this.router.navigate(['/layout/reportDetails/create']);
          return;
        } else {
          this.source = res.data.map((item , index) => {
            return {
              // createTime: '2019-06-10 11:23:54',
              count: item.dataNum,
              id: item.id,
              // remarks: '测试',
              name: item.tableName,
              index
            };
          });
          this.currentId = this.source[0].id;
          this.currentName = this.source[0].name;
          console.log(this.currentName);
          console.log(this.currentId);
          this.router.navigate(['/layout/reportDetails/bulk']);
        }
      },
      err => {
        console.log(err);
      }
    );
  }
}
