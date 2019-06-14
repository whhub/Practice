import { TestBed } from '@angular/core/testing';

import { GetDeviceListService } from './get-device-list.service';

describe('GetDeviceListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceListService = TestBed.get(GetDeviceListService);
    expect(service).toBeTruthy();
  });
});
