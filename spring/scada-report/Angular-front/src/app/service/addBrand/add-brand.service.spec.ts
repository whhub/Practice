import { TestBed } from '@angular/core/testing';

import { AddBrandService } from './add-brand.service';

describe('AddBrandService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddBrandService = TestBed.get(AddBrandService);
    expect(service).toBeTruthy();
  });
});
