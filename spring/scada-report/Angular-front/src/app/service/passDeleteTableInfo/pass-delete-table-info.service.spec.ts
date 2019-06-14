import { TestBed } from '@angular/core/testing';

import { PassDeleteTableInfoService } from './pass-delete-table-info.service';

describe('PassDeleteTableInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PassDeleteTableInfoService = TestBed.get(PassDeleteTableInfoService);
    expect(service).toBeTruthy();
  });
});
