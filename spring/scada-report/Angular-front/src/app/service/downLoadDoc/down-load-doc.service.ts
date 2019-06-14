import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Base} from '../../entity/base';
import {NetServiceHttpclient} from '../../net/net-service-httpclient';

@Injectable({
  providedIn: 'root'
})
export class DownLoadDocService extends NetServiceHttpclient {

  constructor(public httpClient: HttpClient) {
    super(httpClient);
  }
  downLoadDoc(para): any {
    const headers = new HttpHeaders();
    // const token: string = localStorage.getItem('token');
    headers.append('Accept', 'application/octet-stream');
    headers.append('Content-Type', 'application/json');
    console.log(headers);
    return this.get<Base<any>>('/info/download', para, headers);
  }
}
