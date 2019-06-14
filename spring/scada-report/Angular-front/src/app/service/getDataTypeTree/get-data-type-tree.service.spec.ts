import { TestBed } from '@angular/core/testing';

import { GetDataTypeTreeService } from './get-data-type-tree.service';

describe('GetDataTypeTreeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDataTypeTreeService = TestBed.get(GetDataTypeTreeService);
    expect(service).toBeTruthy();
  });
});
