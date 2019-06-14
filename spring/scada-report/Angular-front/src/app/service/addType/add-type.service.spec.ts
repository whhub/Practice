import { TestBed } from '@angular/core/testing';

import { AddTypeService } from './add-type.service';

describe('AddTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddTypeService = TestBed.get(AddTypeService);
    expect(service).toBeTruthy();
  });
});
