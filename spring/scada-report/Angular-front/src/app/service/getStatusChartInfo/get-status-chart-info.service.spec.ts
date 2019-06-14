import { TestBed } from '@angular/core/testing';

import { GetStatusChartInfoService } from './get-status-chart-info.service';

describe('GetStatusChartInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetStatusChartInfoService = TestBed.get(GetStatusChartInfoService);
    expect(service).toBeTruthy();
  });
});
