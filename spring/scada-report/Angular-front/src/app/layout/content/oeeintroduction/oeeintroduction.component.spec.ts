import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OEEIntroductionComponent } from './oeeintroduction.component';

describe('OEEIntroductionComponent', () => {
  let component: OEEIntroductionComponent;
  let fixture: ComponentFixture<OEEIntroductionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OEEIntroductionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OEEIntroductionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
