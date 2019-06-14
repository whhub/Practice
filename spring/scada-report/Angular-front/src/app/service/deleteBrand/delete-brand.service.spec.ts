import { TestBed } from '@angular/core/testing';

import { DeleteBrandService } from './delete-brand.service';

describe('DeleteBrandService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteBrandService = TestBed.get(DeleteBrandService);
    expect(service).toBeTruthy();
  });
});
