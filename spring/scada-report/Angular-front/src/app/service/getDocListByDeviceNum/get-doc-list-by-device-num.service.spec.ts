import { TestBed } from '@angular/core/testing';

import { GetDocListByDeviceNumService } from './get-doc-list-by-device-num.service';

describe('GetDocListByDeviceNumService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDocListByDeviceNumService = TestBed.get(GetDocListByDeviceNumService);
    expect(service).toBeTruthy();
  });
});
