import { TestBed } from '@angular/core/testing';

import { GetStartUpPercentService } from './get-start-up-percent.service';

describe('GetStartUpPercentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetStartUpPercentService = TestBed.get(GetStartUpPercentService);
    expect(service).toBeTruthy();
  });
});
