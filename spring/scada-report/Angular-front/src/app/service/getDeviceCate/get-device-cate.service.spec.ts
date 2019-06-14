import { TestBed } from '@angular/core/testing';

import { GetDeviceCateService } from './get-device-cate.service';

describe('GetDeviceCateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceCateService = TestBed.get(GetDeviceCateService);
    expect(service).toBeTruthy();
  });
});
