import { TestBed } from '@angular/core/testing';

import { GetTableBodyService } from './get-table-body.service';

describe('GetTableBodyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetTableBodyService = TestBed.get(GetTableBodyService);
    expect(service).toBeTruthy();
  });
});
