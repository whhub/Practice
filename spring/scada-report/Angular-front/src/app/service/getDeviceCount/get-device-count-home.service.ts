import { Injectable } from '@angular/core';
import {NetServiceHttpclient} from '../../net/net-service-httpclient';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Base} from '../../entity/base';

@Injectable({
  providedIn: 'root'
})
export class GetDeviceCountHomeService extends NetServiceHttpclient {

  constructor(public httpClient: HttpClient) {
    super(httpClient);
  }
  getDeviceCountHome(): Observable<Base<any>> {
    return this.get<Base<any>>('/ledger/list');
  }
}
