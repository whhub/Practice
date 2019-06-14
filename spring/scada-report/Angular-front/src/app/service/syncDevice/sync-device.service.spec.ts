import { TestBed } from '@angular/core/testing';

import { SyncDeviceService } from './sync-device.service';

describe('SyncDeviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SyncDeviceService = TestBed.get(SyncDeviceService);
    expect(service).toBeTruthy();
  });
});
