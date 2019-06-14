import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCalcuRuleComponent } from './add-calcu-rule.component';

describe('AddCalcuRuleComponent', () => {
  let component: AddCalcuRuleComponent;
  let fixture: ComponentFixture<AddCalcuRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCalcuRuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCalcuRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
