import { TestBed } from '@angular/core/testing';

import { UpdateTableParamService } from './update-table-param.service';

describe('UpdateTableParamService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateTableParamService = TestBed.get(UpdateTableParamService);
    expect(service).toBeTruthy();
  });
});
