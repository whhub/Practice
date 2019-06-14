import {Component, OnInit} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd';
import {GetDataTypeTreeService} from '../../../../service/getDataTypeTree/get-data-type-tree.service';
import {NzFormatEmitEvent, NzTreeComponent, NzTreeNodeOptions} from 'ng-zorro-antd';
import {ViewChild} from '@angular/core';
import {UpdateTableParamService} from '../../../../service/updateTableParam/update-table-param.service';

@Component({
  selector: 'app-create-report',
  templateUrl: './create-report.component.html',
  styleUrls: ['./create-report.component.scss']
})
export class CreateReportComponent implements OnInit {
  @ViewChild('nzTreeComponent') nzTreeComponent: NzTreeComponent;
  dateFormat;
  expandKeys = ['100', '1001'];
  treeValue: string;
  isVisible = false;
  beChecked = [];
  currentIndex;
  nodes: NzTreeNodeOptions[] = [
    // {
    //   title: 'parent 1',
    //   key: '100',
    //   disabled: true,
    //   children: [
    //     {
    //       title: 'parent 1-0',
    //       key: '1001',
    //       disabled: true,
    //       children: [
    //         { title: 'leaf 1-0-0', key: '10010', disableCheckbox: true, isLeaf: true },
    //         { title: 'leaf 1-0-1', key: '10011', isLeaf: true }
    //       ]
    //     },
    //     {
    //       title: 'parent 1-1',
    //       key: '1002',
    //       children: [
    //         { title: 'leaf 1-1-0', key: '10020', isLeaf: true },
    //         { title: 'leaf 1-1-1', key: '10021', isLeaf: true }
    //       ]
    //     }
    //   ]
    // }
  ];

  listOfData = [
    {
      index: 0,
      name: '',
      isUse: 'false',
      key: '',
      tableType: '0',
      linkData: '',
      fixedValue: ''
    }
  ];
  selectedValue;
  value;

  constructor(
    private mes: NzMessageService,
    private getDataTypeTreeService: GetDataTypeTreeService,
    private updateTableParamService: UpdateTableParamService
  ) {
  }

  ngOnInit() {
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

  addParam() {
    const lastItemIndex = this.listOfData.length - 1;
    const lastItem = this.listOfData[lastItemIndex];
    if (lastItem.name === '' || lastItem.key === '') {
      this.mes.warning('上一项还有必填项未填，不能添加');
      return;
    } else {
      this.listOfData = [...this.listOfData, {
        index: this.listOfData.length + 1,
        name: '',
        isUse: 'false',
        key: '',
        tableType: '0',
        linkData: '',
        fixedValue: ''
      }];
    }
  }

  deletePara() {
    if (this.listOfData.length === 1) {
      this.mes.warning('再删就没有咯！');
      return;
    }
  }

  showModal(e): void {
    const index = e.currentTarget.getAttribute('data-index');
    console.log(index);
    this.currentIndex = index;
    this.isVisible = true;
  }

  handleOk = (): void => {
    if (this.beChecked.length > 1) {
      this.mes.warning('只能关联一个！');
      return;
    }
    console.log(this.listOfData);
    console.log(this.listOfData[this.currentIndex]);
    console.log(this.listOfData[this.currentIndex].linkData);
    this.listOfData[this.currentIndex].linkData = this.beChecked[0].title;
    this.isVisible = false;
  };

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  // node
  nzClick(event: NzFormatEmitEvent): void {
    console.log(event);
  }

  nzCheck(event: NzFormatEmitEvent): void {
    console.log(event);
    this.beChecked = event.nodes;
  }

  // nzSelectedKeys change
  nzSelect(keys: string[]): void {
    console.log(keys, this.nzTreeComponent.getSelectedNodeList());
  }

  submit() {
    console.log(this.listOfData);
    const updateTableParam = {
      id: 1,
      tableName: '控制状态',
      tableMsg: '对设备状态的统计分析',

      addTableParam: [
        {
          paramName: '设备状态',
          isUse: true,
          keyName: 'equipDes',
          paramType: '数字',
          connectType: '通用类数据',
          connectName: 'startupTime',
          fixedValue: null
        },
        {
          'paramName': '设备类别',
          'isUse': true,
          'keyName': 'equipDes',
          'paramType': '数字',
          'connectType': '通用类数据',
          'connectName': 'startupTime',
          'fixedValue': null
        }
      ],
    };
    // this.updateTableParamService.updateTableParam(updateTableParam).subscribe(
    //   res => {
    //     console.log(res);
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
  }
}
