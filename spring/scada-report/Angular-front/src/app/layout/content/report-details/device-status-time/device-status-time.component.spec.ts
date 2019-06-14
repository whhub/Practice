import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceStatusTimeComponent } from './device-status-time.component';

describe('DeviceStatusTimeComponent', () => {
  let component: DeviceStatusTimeComponent;
  let fixture: ComponentFixture<DeviceStatusTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceStatusTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceStatusTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
