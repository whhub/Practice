import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Base} from '../../entity/base';
import {NetServiceHttpclient} from '../../net/net-service-httpclient';

@Injectable({
  providedIn: 'root'
})
export class GetDeviceStatusWatchInfoService extends NetServiceHttpclient {

  constructor(public httpClient: HttpClient) {
    super(httpClient);
  }
  getDeviceStatusWatchInfo(): Observable<Base<any>> {
    return this.get<Base<any>>('/home/status');
  }
}
