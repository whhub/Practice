import { TestBed } from '@angular/core/testing';

import { DownLoadDocService } from './down-load-doc.service';

describe('DownLoadDocService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DownLoadDocService = TestBed.get(DownLoadDocService);
    expect(service).toBeTruthy();
  });
});
