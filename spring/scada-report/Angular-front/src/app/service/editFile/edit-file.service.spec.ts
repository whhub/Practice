import { TestBed } from '@angular/core/testing';

import { EditFileService } from './edit-file.service';

describe('EditFileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditFileService = TestBed.get(EditFileService);
    expect(service).toBeTruthy();
  });
});
