import { TestBed } from '@angular/core/testing';

import { GetErrorCodeByBrandIdService } from './get-error-code-by-brand-id.service';

describe('GetErrorCodeByBrandIdService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetErrorCodeByBrandIdService = TestBed.get(GetErrorCodeByBrandIdService);
    expect(service).toBeTruthy();
  });
});
