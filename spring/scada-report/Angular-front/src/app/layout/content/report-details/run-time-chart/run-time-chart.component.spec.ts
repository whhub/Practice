import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RunTimeChartComponent } from './run-time-chart.component';

describe('RunTimeChartComponent', () => {
  let component: RunTimeChartComponent;
  let fixture: ComponentFixture<RunTimeChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RunTimeChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RunTimeChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
