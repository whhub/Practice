import { TestBed } from '@angular/core/testing';

import { GetBrandListService } from './get-brand-list.service';

describe('GetBrandListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetBrandListService = TestBed.get(GetBrandListService);
    expect(service).toBeTruthy();
  });
});
