import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceCountChartComponent } from './device-count-chart.component';

describe('DeviceCountChartComponent', () => {
  let component: DeviceCountChartComponent;
  let fixture: ComponentFixture<DeviceCountChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceCountChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceCountChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
