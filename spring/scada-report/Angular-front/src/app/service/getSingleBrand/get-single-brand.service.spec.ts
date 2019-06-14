import { TestBed } from '@angular/core/testing';

import { GetSingleBrandService } from './get-single-brand.service';

describe('GetSingleBrandService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetSingleBrandService = TestBed.get(GetSingleBrandService);
    expect(service).toBeTruthy();
  });
});
