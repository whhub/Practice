import { TestBed } from '@angular/core/testing';

import { DeleteDocService } from './delete-doc.service';

describe('DeleteDocService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteDocService = TestBed.get(DeleteDocService);
    expect(service).toBeTruthy();
  });
});
