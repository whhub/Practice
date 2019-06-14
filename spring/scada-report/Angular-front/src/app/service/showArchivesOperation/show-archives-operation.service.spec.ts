import { TestBed } from '@angular/core/testing';

import { ShowArchivesOperationService } from './show-archives-operation.service';

describe('ShowArchivesOperationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShowArchivesOperationService = TestBed.get(ShowArchivesOperationService);
    expect(service).toBeTruthy();
  });
});
