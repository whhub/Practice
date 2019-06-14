import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceControlStatusComponent } from './device-control-status.component';

describe('DeviceControlStatusComponent', () => {
  let component: DeviceControlStatusComponent;
  let fixture: ComponentFixture<DeviceControlStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceControlStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceControlStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
