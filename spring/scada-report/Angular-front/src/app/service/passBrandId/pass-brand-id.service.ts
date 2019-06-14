import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PassBrandIdService {
  public passBrandId: Subject<Object> = new Subject<Object>();
  constructor() { }
}
