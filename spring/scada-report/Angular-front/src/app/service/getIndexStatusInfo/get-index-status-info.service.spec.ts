import { TestBed } from '@angular/core/testing';

import { GetIndexStatusInfoService } from './get-index-status-info.service';

describe('GetIndexStatusInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetIndexStatusInfoService = TestBed.get(GetIndexStatusInfoService);
    expect(service).toBeTruthy();
  });
});
