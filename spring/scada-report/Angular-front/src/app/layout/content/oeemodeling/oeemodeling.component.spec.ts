import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OEEModelingComponent } from './oeemodeling.component';

describe('OEEModelingComponent', () => {
  let component: OEEModelingComponent;
  let fixture: ComponentFixture<OEEModelingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OEEModelingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OEEModelingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
