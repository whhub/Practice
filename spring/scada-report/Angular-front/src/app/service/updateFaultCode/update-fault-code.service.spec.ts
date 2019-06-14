import { TestBed } from '@angular/core/testing';

import { UpdateFaultCodeService } from './update-fault-code.service';

describe('UpdateFaultCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateFaultCodeService = TestBed.get(UpdateFaultCodeService);
    expect(service).toBeTruthy();
  });
});
