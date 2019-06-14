import { TestBed } from '@angular/core/testing';

import { GetDeviceCountHomeService } from './get-device-count-home.service';

describe('GetDeviceCountHomeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDeviceCountHomeService = TestBed.get(GetDeviceCountHomeService);
    expect(service).toBeTruthy();
  });
});
