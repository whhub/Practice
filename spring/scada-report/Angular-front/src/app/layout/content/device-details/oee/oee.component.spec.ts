import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OEEComponent } from './oee.component';

describe('OEEComponent', () => {
  let component: OEEComponent;
  let fixture: ComponentFixture<OEEComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OEEComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OEEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
