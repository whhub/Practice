import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PassDeleteTableInfoService {
  public passDeleteTableInfo: Subject<string> = new Subject<string>();
  constructor() { }
}
