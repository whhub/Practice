import { TestBed } from '@angular/core/testing';

import { DeleteReportService } from './delete-report.service';

describe('DeleteReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteReportService = TestBed.get(DeleteReportService);
    expect(service).toBeTruthy();
  });
});
