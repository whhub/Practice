import { TestBed } from '@angular/core/testing';

import { GetTypeTreeService } from './get-type-tree.service';

describe('GetTypeTreeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetTypeTreeService = TestBed.get(GetTypeTreeService);
    expect(service).toBeTruthy();
  });
});
