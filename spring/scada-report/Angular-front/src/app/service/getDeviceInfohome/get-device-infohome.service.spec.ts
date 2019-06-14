import { TestBed } from '@angular/core/testing';

import { GetDeviceInfohomeService } from './get-device-infohome.service';

describe('GetDeviceInfohomeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceInfohomeService = TestBed.get(GetDeviceInfohomeService);
    expect(service).toBeTruthy();
  });
});
