import { TestBed } from '@angular/core/testing';

import { GetSingleTableService } from './get-single-table.service';

describe('GetSingleTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetSingleTableService = TestBed.get(GetSingleTableService);
    expect(service).toBeTruthy();
  });
});
