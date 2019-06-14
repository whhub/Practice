import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceStatusChartComponent } from './device-status-chart.component';

describe('DeviceStatusChartComponent', () => {
  let component: DeviceStatusChartComponent;
  let fixture: ComponentFixture<DeviceStatusChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceStatusChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceStatusChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
