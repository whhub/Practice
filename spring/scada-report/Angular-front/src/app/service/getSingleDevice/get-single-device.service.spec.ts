import { TestBed } from '@angular/core/testing';

import { GetSingleDeviceService } from './get-single-device.service';

describe('GetSingleDeviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetSingleDeviceService = TestBed.get(GetSingleDeviceService);
    expect(service).toBeTruthy();
  });
});
