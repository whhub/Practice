import { TestBed } from '@angular/core/testing';

import { GetTableHeaderService } from './get-table-header.service';

describe('GetTableHeaderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetTableHeaderService = TestBed.get(GetTableHeaderService);
    expect(service).toBeTruthy();
  });
});
