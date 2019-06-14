import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LayoutRoutingModule} from './layout-routing.module';
import {LayoutComponent} from './layout.component';
import { DeviceSurveyComponent } from './content/device-survey/device-survey.component';
import { StatusTimeComponent } from './content/status-time/status-time.component';
import { DeviceControlStatusComponent } from './content/device-control-status/device-control-status.component';
import { AlgorithmModelComponent } from './content/algorithm-model/algorithm-model.component';
import {DeleteDeviceCountService} from '../service/deleteDeviceCount/delete-device-count.service';
import {GetSingleDeviceService} from '../service/getSingleDevice/get-single-device.service';
import {GetDeviceInfohomeService} from '../service/getDeviceInfohome/get-device-infohome.service';
import {GetDeviceCateService} from '../service/getDeviceCate/get-device-cate.service';
import {GetTableListService} from '../service/getTableList/get-table-list.service';
import {AddTableService} from '../service/addTable/add-table.service';
import {GetSingleTableService} from '../service/getSingleTable/get-single-table.service';
import {GetDeviceStatusWatchInfoService} from '../service/getDeviceStatusWatchInfo/get-device-status-watch-info.service';
import {GetDeviceCatePercentService} from '../service/getDeviceCatePercent/get-device-cate-percent.service';
import {GetIndexStatusInfoService} from '../service/getIndexStatusInfo/get-index-status-info.service';
import {GetStartUpPercentService} from '../service/getStartUpPercent/get-start-up-percent.service';
import {UploadFileService} from '../service/uploadFile/upload-file.service';
import {GetBrandListService} from '../service/getBrandList/get-brand-list.service';
import {GetDocDeviceTypeService} from '../service/getDocDeviceType/get-doc-device-type.service';
import {SyncDeviceService} from '../service/syncDevice/sync-device.service';
import {GetDevicebyNumService} from '../service/getDevicebyNum/get-deviceby-num.service';
import {GetDocListService} from '../service/getDocList/get-doc-list.service';
import {GetSingleFileService} from '../service/getSingleFile/get-single-file.service';
import {EditFileService} from '../service/editFile/edit-file.service';
import {AddTypeService} from '../service/addType/add-type.service';
import {GetLastFiveYearCountService} from '../service/getLastFiveYearCount/get-last-five-year-count.service';
import {GetStatusChartInfoService} from '../service/getStatusChartInfo/get-status-chart-info.service';
import {GetDeviceListService} from '../service/getDeviceList/get-device-list.service';
import {GetTypeListService} from '../service/getTypeList/get-type-list.service';
import {AddBrandService} from '../service/addBrand/add-brand.service';
import {GetSingleBrandService} from '../service/getSingleBrand/get-single-brand.service';
import {PassBrandIdService} from '../service/passBrandId/pass-brand-id.service';
import {DeleteBrandService} from '../service/deleteBrand/delete-brand.service';
import {GetErrorCodeByBrandIdService} from '../service/getErrorCodeByBrandId/get-error-code-by-brand-id.service';
import { EventMangementComponent } from './content/device-details/event-mangement/event-mangement.component';
import {UpdateFaultCodeService} from '../service/updateFaultCode/update-fault-code.service';
import {AddErrorCodeService} from '../service/addErrorCode/add-error-code.service';
import {DeleteErrorCodeService} from '../service/deleteErrorCode/delete-error-code.service';
import {GetDocListByDeviceNumService} from '../service/getDocListByDeviceNum/get-doc-list-by-device-num.service';
import {DownLoadDocService} from '../service/downLoadDoc/down-load-doc.service';
import { UpdateCateComponent } from './content/update-cate/update-cate.component';
import {UpdateCateService} from '../service/updateCate/update-cate.service';
import {GetTableHeaderService} from '../service/getTableHeader/get-table-header.service';
import {GetTableBodyService} from '../service/getTableBody/get-table-body.service';
import {PassReportIdService} from '../service/passReportId/pass-report-id.service';
import {DeleteReportService} from '../service/deleteReport/delete-report.service';
import {PassDeleteTableInfoService} from '../service/passDeleteTableInfo/pass-delete-table-info.service';
import {GetDataTypeTreeService} from '../service/getDataTypeTree/get-data-type-tree.service';
import {UpdateTableParamService} from '../service/updateTableParam/update-table-param.service';

@NgModule({
  declarations: [
    LayoutComponent,
    DeviceSurveyComponent,
    StatusTimeComponent,
    DeviceControlStatusComponent,
    AlgorithmModelComponent,
    UpdateCateComponent,
  ],
  imports: [
    CommonModule,
    LayoutRoutingModule
  ],
  providers: [
    DeleteDeviceCountService,
    GetSingleDeviceService,
    GetDeviceInfohomeService,
    GetDeviceCateService,
    GetTableListService,
    AddTableService,
    GetSingleTableService,
    GetDeviceStatusWatchInfoService,
    GetDeviceCatePercentService,
    GetIndexStatusInfoService,
    GetStartUpPercentService,
    UploadFileService,
    GetBrandListService,
    GetDocDeviceTypeService,
    SyncDeviceService,
    GetDevicebyNumService,
    GetDocListService,
    GetSingleFileService,
    EditFileService,
    AddTypeService,
    GetLastFiveYearCountService,
    GetStatusChartInfoService,
    GetDeviceListService,
    GetTypeListService,
    AddBrandService,
    GetSingleBrandService,
    PassBrandIdService,
    DeleteBrandService,
    GetErrorCodeByBrandIdService,
    UpdateFaultCodeService,
    AddErrorCodeService,
    DeleteErrorCodeService,
    GetDocListByDeviceNumService,
    DownLoadDocService,
    UpdateCateService,
    GetTableHeaderService,
    GetTableBodyService,
    PassReportIdService,
    DeleteReportService,
    PassDeleteTableInfoService,
    GetDataTypeTreeService,
    UpdateTableParamService
  ]
})
export class LayoutModule { }
