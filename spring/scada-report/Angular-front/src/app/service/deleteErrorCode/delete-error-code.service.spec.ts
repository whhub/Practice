import { TestBed } from '@angular/core/testing';

import { DeleteErrorCodeService } from './delete-error-code.service';

describe('DeleteErrorCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteErrorCodeService = TestBed.get(DeleteErrorCodeService);
    expect(service).toBeTruthy();
  });
});
