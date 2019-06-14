import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Base} from '../../entity/base';
import {NetServiceHttpclient} from '../../net/net-service-httpclient';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService extends NetServiceHttpclient {
  constructor(public httpClient: HttpClient) {
    super(httpClient);
  }
  protected get headers(): HttpHeaders {
    // const token: string = localStorage.getItem('token');
    const headers = new HttpHeaders();
    headers.append('Accept', 'multipart/form-data');
    headers.append('Content-Type', 'multipart/form-data');
    // headers.append("Authorization", token);
    return headers;
  }
  uploadFile(para): Observable<Base<any>> {
    return this.post<Base<any>>('/doc/add', para);
  }
}
