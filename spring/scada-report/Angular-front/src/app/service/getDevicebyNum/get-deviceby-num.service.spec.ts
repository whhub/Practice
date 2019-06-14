import { TestBed } from '@angular/core/testing';

import { GetDevicebyNumService } from './get-deviceby-num.service';

describe('GetDevicebyNumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDevicebyNumService = TestBed.get(GetDevicebyNumService);
    expect(service).toBeTruthy();
  });
});
