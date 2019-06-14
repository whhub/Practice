import { TestBed } from '@angular/core/testing';

import { GetTableListService } from './get-table-list.service';

describe('GetTableListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetTableListService = TestBed.get(GetTableListService);
    expect(service).toBeTruthy();
  });
});
