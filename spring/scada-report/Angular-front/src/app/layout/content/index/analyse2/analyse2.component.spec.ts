import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Analyse2Component } from './analyse2.component';

describe('Analyse2Component', () => {
  let component: Analyse2Component;
  let fixture: ComponentFixture<Analyse2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Analyse2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Analyse2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
