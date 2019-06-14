import { TestBed } from '@angular/core/testing';

import { PassBrandIdService } from './pass-brand-id.service';

describe('PassBrandIdService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PassBrandIdService = TestBed.get(PassBrandIdService);
    expect(service).toBeTruthy();
  });
});
