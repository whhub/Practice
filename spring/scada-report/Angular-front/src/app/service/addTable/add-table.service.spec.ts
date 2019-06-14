import { TestBed } from '@angular/core/testing';

import { AddTableService } from './add-table.service';

describe('AddTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddTableService = TestBed.get(AddTableService);
    expect(service).toBeTruthy();
  });
});
