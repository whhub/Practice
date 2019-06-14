import { TestBed } from '@angular/core/testing';

import { AddErrorCodeService } from './add-error-code.service';

describe('AddErrorCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddErrorCodeService = TestBed.get(AddErrorCodeService);
    expect(service).toBeTruthy();
  });
});
