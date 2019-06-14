import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from '@angular/router';
import {Routes} from '@angular/router';
import {RealTimeDataComponent} from './content/real-time-data/real-time-data.component';
import {LayoutComponent} from './layout.component';
import {MyComonModule} from '../comon/comon.module';
import {IndexComponent} from './content/index/index.component';
import {ChartsModule} from '../charts/charts.module';
import {BarComponent} from './content/index/bar/bar.component';
import {PieComponent} from './content/index/pie/pie.component';
import {StatisticalAnalysisComponent} from './content/statistical-analysis/statistical-analysis.component';
import {DeviceSurveyComponent} from './content/device-survey/device-survey.component';
import {StatusTimeComponent} from './content/status-time/status-time.component';
import {DeviceControlStatusComponent} from './content/device-control-status/device-control-status.component';
import {RunComponent} from './content/index/run/run.component';
import {ViserModule} from 'viser-ng';
import {OffComponent} from './content/index/off/off.component';
import {ErrorComponent} from './content/index/error/error.component';
import {Analyse1Component} from './content/index/analyse1/analyse1.component';
import {Analyse2Component} from './content/index/analyse2/analyse2.component';
import {DeviceCountComponent} from './content/device-count/device-count.component';
import {DeviceCountChartComponent} from './content/device-count/device-count-chart/device-count-chart.component';
import {DeviceStatusChartComponent} from './content/device-count/device-status-chart/device-status-chart.component';
import {DeviceDetailsComponent} from './content/device-details/device-details.component';
import {GetActiveDirective} from '../directive/getActive/get-active.directive';
import {DeviceInfoComponent} from './content/device-details/device-info/device-info.component';
import {ArchivesListComponent} from './content/device-details/archives-list/archives-list.component';
import {ArchivesUploadComponent} from './content/device-details/archives-list/archives-upload/archives-upload.component';
import {UploadPartComponent} from './content/device-details/archives-list/archives-upload/upload-part/upload-part.component';
import {LinkPartComponent} from './content/device-details/archives-list/archives-upload/link-part/link-part.component';
import {ShowArchivesOperationService} from '../service/showArchivesOperation/show-archives-operation.service';
import {MaintenanceLogComponent} from './content/device-details/maintenance-log/maintenance-log.component';
import {RealTimeComponent} from './content/device-details/real-time/real-time.component';
import {OEEComponent} from './content/device-details/oee/oee.component';
import {DateComponent} from './content/date/date.component';
import {MyTaskComponent} from './content/my-task/my-task.component';
import {DeviceCateComponent} from './content/device-cate/device-cate.component';
import {AddCateComponent} from './content/add-cate/add-cate.component';
import {AddBrandComponent} from './content/add-brand/add-brand.component';
import {AlgorithmModelComponent} from './content/algorithm-model/algorithm-model.component';
import {OEEIntroductionComponent} from './content/oeeintroduction/oeeintroduction.component';
import {OEEModelingComponent} from './content/oeemodeling/oeemodeling.component';
import {AddCalcuRuleComponent} from './content/add-calcu-rule/add-calcu-rule.component';
import {AddDeviceComponent} from './content/add-device/add-device.component';
import {FileMangementComponent} from './content/file-mangement/file-mangement.component';
import {FileUploadComponent} from './content/file-upload/file-upload.component';
import {GetDeviceCountHomeService} from '../service/getDeviceCount/get-device-count-home.service';
import {UpdateDeviceCountService} from '../service/updateDeviceCount/update-device-count.service';
import {UpdateDeviceComponent} from './content/update-device/update-device.component';
import {TestComponent} from './test/test.component';
import {FileEditComponent} from './content/file-edit/file-edit.component';
import {EventMangementComponent} from './content/device-details/event-mangement/event-mangement.component';
import {UpdateCateComponent} from './content/update-cate/update-cate.component';


const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'index' ,
        pathMatch: 'full'
      },
      {
        path: 'index',
        component: IndexComponent
      },
      {
        path: 'realTimeData',
        component: RealTimeDataComponent
      },
      {
        path: 'statisticalAnalysis',
        component: StatisticalAnalysisComponent
      },
      {
        path: 'deviceSurvey',
        component: DeviceSurveyComponent
      },
      {
        path: 'statusTime',
        component: StatusTimeComponent
      },
      {
        path: 'deviceControlStatus',
        component: DeviceControlStatusComponent
      },
      {
        path: 'deviceCount',
        component: DeviceCountComponent
      },
      {
        path: 'deviceDetails',
        component: DeviceDetailsComponent
      },
      {
        path: 'date',
        component: DateComponent
      },
      {
        path: 'myTask',
        component: MyTaskComponent
      },
      {
        path: 'deviceCate',
        component: DeviceCateComponent
      },
      {
        path: 'addCate',
        component: AddCateComponent
      },
      {
        path: 'updateCate',
        component: UpdateCateComponent
      },
      {
        path: 'addBrand',
        component: AddBrandComponent
      },
      {
        path: 'algorithmModel',
        component: AlgorithmModelComponent
      },
      {
        path: 'oeeIntro',
        component: OEEIntroductionComponent
      },
      {
        path: 'addDevice',
        component: AddDeviceComponent
      },
      {
        path: 'updateDevice',
        component: UpdateDeviceComponent
      },
      {
        path: 'oeeModel',
        component: OEEModelingComponent
      },
      {
        path: 'addCalcuRule',
        component: AddCalcuRuleComponent
      },
      {
        path: 'fileMangement',
        loadChildren: './content/file-mangement/file-mangement.module#FileMangementModule'
      },
      {
        path: 'fileUpload',
        component: FileUploadComponent
      },
      {
        path: 'test',
        component: TestComponent
      },
      {
        path: 'fileEdit',
        component: FileEditComponent
      },
      {
        path: 'reportDetails',
        loadChildren: './content/report-details/report-details.module#ReportDetailsModule'
      }
    ]
  },
];
@NgModule({
  declarations: [
    TestComponent,
    UpdateDeviceComponent,
    DeviceCountComponent,
    DeviceDetailsComponent,
    RunComponent,
    RealTimeDataComponent,
    IndexComponent,
    BarComponent,
    PieComponent,
    OffComponent,
    ErrorComponent,
    StatisticalAnalysisComponent,
    Analyse1Component,
    Analyse2Component,
    DeviceCountChartComponent,
    DeviceStatusChartComponent,
    GetActiveDirective,
    DeviceInfoComponent,
    ArchivesListComponent,
    ArchivesUploadComponent,
    UploadPartComponent,
    LinkPartComponent,
    MaintenanceLogComponent,
    RealTimeComponent,
    OEEComponent,
    DateComponent,
    MyTaskComponent,
    DeviceCateComponent,
    AddCateComponent,
    AddBrandComponent,
    OEEIntroductionComponent,
    OEEModelingComponent,
    AddCalcuRuleComponent,
    AddDeviceComponent,
    FileUploadComponent,
    FileEditComponent,
    EventMangementComponent
  ],
  imports: [
    ChartsModule,
    MyComonModule,
    CommonModule,
    ViserModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule,
    MyComonModule,
    ViserModule,
    RunComponent,
    // RealTimeDataComponent
  ],
  providers: [
    ShowArchivesOperationService,
    GetDeviceCountHomeService,
    UpdateDeviceCountService
  ]
})
export class LayoutRoutingModule { }
