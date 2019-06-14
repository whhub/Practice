import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Base} from '../../entity/base';
import {NetServiceHttpclient} from '../../net/net-service-httpclient';

@Injectable({
  providedIn: 'root'
})
export class GetBrandListService extends NetServiceHttpclient{

  constructor(public httpClient: HttpClient) {
    super(httpClient);
  }
  getBrandList(para): Observable<Base<any>> {
    return this.get<Base<any>>('/doc/equipBrands', para);
  }
}
