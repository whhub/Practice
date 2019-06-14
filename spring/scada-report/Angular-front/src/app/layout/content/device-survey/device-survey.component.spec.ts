import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceSurveyComponent } from './device-survey.component';

describe('DeviceSurveyComponent', () => {
  let component: DeviceSurveyComponent;
  let fixture: ComponentFixture<DeviceSurveyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceSurveyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
