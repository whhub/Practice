import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShowArchivesOperationService {
  public showArchivesOperation: Subject<boolean> = new Subject<boolean>();
  constructor() { }
}
