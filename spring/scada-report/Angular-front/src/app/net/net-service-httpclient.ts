import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SERVER_URL} from '../utils/config';
import {Observable} from 'rxjs';
import {HttpParams} from '@angular/common/http/src/params';

export abstract class NetServiceHttpclient {
  protected baseUrl = SERVER_URL;

  constructor(public httpClient: HttpClient) {
  }

  protected get headers(): HttpHeaders {
    // const token: string = localStorage.getItem('token');
    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    // headers.append("Authorization", token);
    return headers;
  }

  protected get<T>(relativeUrl: string,
                   params?: HttpParams | { [param: string]: string | string[] },
                   headers?: HttpHeaders): Observable<T> {
    return this.httpClient.get<T>(this.baseUrl + relativeUrl, {
      headers: headers || this.headers,
      params: params
    });
  }

  protected post<T>(relativeUrl: string,
                    body?: any,
                    params?: HttpParams | { [param: string]: string | string[] },
                    headers?: HttpHeaders): Observable<T> {
    return this.httpClient.post<T>(this.baseUrl + relativeUrl, body, {
      headers: headers || this.headers,
      params: params
    });
  }

  protected put<T>(relativeUrl: string,
                   body?: any,
                   params?: HttpParams | { [param: string]: string | string[] },
                   headers?: HttpHeaders): Observable<T> {
    return this.httpClient.put<T>(this.baseUrl + relativeUrl, body, {
      headers: headers || this.headers,
      params: params
    });
  }

  protected delete<T>(relativeUrl: string,
                      params?: HttpParams | { [param: string]: string | string[] },
                      headers?: HttpHeaders): Observable<T> {
    return this.httpClient.delete<T>(this.baseUrl + relativeUrl, {
      headers: headers || this.headers,
      params: params
    });
  }
}
