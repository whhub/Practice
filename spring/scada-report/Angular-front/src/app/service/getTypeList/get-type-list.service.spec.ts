import { TestBed } from '@angular/core/testing';

import { GetTypeListService } from './get-type-list.service';

describe('GetTypeListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetTypeListService = TestBed.get(GetTypeListService);
    expect(service).toBeTruthy();
  });
});
