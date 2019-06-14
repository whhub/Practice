import { TestBed } from '@angular/core/testing';

import { UpdateDeviceCountService } from './update-device-count.service';

describe('UpdateDeviceCountService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateDeviceCountService = TestBed.get(UpdateDeviceCountService);
    expect(service).toBeTruthy();
  });
});
