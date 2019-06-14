import { TestBed } from '@angular/core/testing';

import { PassReportIdService } from './pass-report-id.service';

describe('PassReportIdService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PassReportIdService = TestBed.get(PassReportIdService);
    expect(service).toBeTruthy();
  });
});
