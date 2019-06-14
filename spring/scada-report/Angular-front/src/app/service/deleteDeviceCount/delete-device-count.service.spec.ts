import { TestBed } from '@angular/core/testing';

import { DeleteDeviceCountService } from './delete-device-count.service';

describe('DeleteDeviceCountService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteDeviceCountService = TestBed.get(DeleteDeviceCountService);
    expect(service).toBeTruthy();
  });
});
