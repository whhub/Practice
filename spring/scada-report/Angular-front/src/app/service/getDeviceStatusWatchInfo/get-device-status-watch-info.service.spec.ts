import { TestBed } from '@angular/core/testing';

import { GetDeviceStatusWatchInfoService } from './get-device-status-watch-info.service';

describe('GetDeviceStatusWatchInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceStatusWatchInfoService = TestBed.get(GetDeviceStatusWatchInfoService);
    expect(service).toBeTruthy();
  });
});
