import { TestBed } from '@angular/core/testing';

import { GetLastFiveYearCountService } from './get-last-five-year-count.service';

describe('GetLastFiveYearCountService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetLastFiveYearCountService = TestBed.get(GetLastFiveYearCountService);
    expect(service).toBeTruthy();
  });
});
