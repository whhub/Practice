import { TestBed } from '@angular/core/testing';

import { GetBrandByCateService } from './get-brand-by-cate.service';

describe('GetBrandByCateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetBrandByCateService = TestBed.get(GetBrandByCateService);
    expect(service).toBeTruthy();
  });
});
