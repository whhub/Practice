import { TestBed } from '@angular/core/testing';

import { GetDeviceCatePercentService } from './get-device-cate-percent.service';

describe('GetDeviceCatePercentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceCatePercentService = TestBed.get(GetDeviceCatePercentService);
    expect(service).toBeTruthy();
  });
});
