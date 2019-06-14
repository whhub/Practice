import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {FormsModule} from '@angular/forms';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'layout' ,
    pathMatch: 'full'
  },
  {
    path: 'layout',
    loadChildren: './layout/layout.module#LayoutModule'
  }

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [
    RouterModule,
    FormsModule
  ]
})
export class AppRoutingModule { }
