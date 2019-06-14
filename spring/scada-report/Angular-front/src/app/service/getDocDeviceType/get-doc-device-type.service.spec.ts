import { TestBed } from '@angular/core/testing';

import { GetDocDeviceTypeService } from './get-doc-device-type.service';

describe('GetDocDeviceTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDocDeviceTypeService = TestBed.get(GetDocDeviceTypeService);
    expect(service).toBeTruthy();
  });
});
