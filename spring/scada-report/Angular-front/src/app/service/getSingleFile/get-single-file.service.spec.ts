import { TestBed } from '@angular/core/testing';

import { GetSingleFileService } from './get-single-file.service';

describe('GetSingleFileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetSingleFileService = TestBed.get(GetSingleFileService);
    expect(service).toBeTruthy();
  });
});
