import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceCateComponent } from './device-cate.component';

describe('DeviceCateComponent', () => {
  let component: DeviceCateComponent;
  let fixture: ComponentFixture<DeviceCateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceCateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceCateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
