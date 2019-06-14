import { TestBed } from '@angular/core/testing';

import { GetDocListService } from './get-doc-list.service';

describe('GetDocListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetDocListService = TestBed.get(GetDocListService);
    expect(service).toBeTruthy();
  });
});
