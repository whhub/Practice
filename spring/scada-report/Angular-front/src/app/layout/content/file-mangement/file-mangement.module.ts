import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FileMangementComponent} from './file-mangement.component';
import {Routes} from '@angular/router';
import {RouterModule} from '@angular/router';
import {MyComonModule} from '../../../comon/comon.module';
import { FileHomeComponent } from './file-home/file-home.component';
import { FileDetailsComponent } from './file-details/file-details.component';
import {GetDocDeviceTypeService} from '../../../service/getDocDeviceType/get-doc-device-type.service';
import {GetBrandByCateService} from '../../../service/getBrandByCate/get-brand-by-cate.service';
import { AddTypePageComponent } from './add-type-page/add-type-page.component';
import { ErrorCodeComponent } from './error-code/error-code.component';
import { SingleBrandComponent } from './single-brand/single-brand.component';
import {GetTypeTreeService} from '../../../service/getTypeTree/get-type-tree.service';
import {DeleteDocService} from '../../../service/deleteDoc/delete-doc.service';

const routes: Routes = [
  {
    path: '',
    component: FileMangementComponent,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: 'home',
        component: FileHomeComponent
      },
      {
        path: 'addType',
        component: AddTypePageComponent
      },
      {
        path: 'fileDetails',
        component: FileDetailsComponent
      },
      {
        path: 'error',
        component: ErrorCodeComponent
      },
      {
        path: 'singleBrand',
        component: SingleBrandComponent
      }
    ]
  }
];
@NgModule({
  declarations: [
    FileMangementComponent,
    FileHomeComponent,
    FileDetailsComponent,
    AddTypePageComponent,
    ErrorCodeComponent,
    SingleBrandComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MyComonModule
  ],
  providers: [
    GetDocDeviceTypeService,
    GetBrandByCateService,
    GetTypeTreeService,
    DeleteDocService
  ]
})
export class FileMangementModule { }
