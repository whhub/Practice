import { TestBed } from '@angular/core/testing';

import { UpdateCateService } from './update-cate.service';

describe('UpdateCateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateCateService = TestBed.get(UpdateCateService);
    expect(service).toBeTruthy();
  });
});
