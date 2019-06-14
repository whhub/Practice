import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ReportDetailsComponent} from './report-details.component';
import {Router, RouterModule, Routes} from '@angular/router';
import {MyComonModule} from '../../../comon/comon.module';
import { CreateReportComponent } from './create-report/create-report.component';
import { BulkAnalysisComponent } from './bulk-analysis/bulk-analysis.component';
import { ControlStatusComponent } from './control-status/control-status.component';
import { DeviceStatusTimeComponent } from './device-status-time/device-status-time.component';
import { RunTimeChartComponent } from './run-time-chart/run-time-chart.component';
import { ProduceStatusComponent } from './produce-status/produce-status.component';
import {GetTableListService} from '../../../service/getTableList/get-table-list.service';


const routes: Routes = [
  {
    path: '',
    component: ReportDetailsComponent,
    children: [
      {
        path: '',
        redirectTo: 'create' ,
        pathMatch: 'full'
      },
      {
        path: 'create',
        component: CreateReportComponent
      },
      {
        path: 'bulk',
        component: BulkAnalysisComponent
      },
      {
        path: 'control',
        component: ControlStatusComponent
      },
      {
        path: 'deviceStatusTime',
        component: DeviceStatusTimeComponent
      },
      {
        path: 'runTimeChart',
        component: RunTimeChartComponent
      },
      {
        path: 'produce',
        component: ProduceStatusComponent
      }
    ]
  },
];
@NgModule({
  declarations: [
    ReportDetailsComponent,
    CreateReportComponent,
    BulkAnalysisComponent,
    ControlStatusComponent,
    DeviceStatusTimeComponent,
    RunTimeChartComponent,
    ProduceStatusComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MyComonModule
  ],
  providers: [
    GetTableListService
  ]
})
export class ReportDetailsModule { }
