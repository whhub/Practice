import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgxEchartsModule} from 'ngx-echarts';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    NgxEchartsModule
  ],
  exports: [NgxEchartsModule]
})
export class ChartsModule { }
