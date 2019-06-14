import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PassReportIdService {
   public passReportId: Subject<string> = new Subject<string>();
  constructor() { }
}
