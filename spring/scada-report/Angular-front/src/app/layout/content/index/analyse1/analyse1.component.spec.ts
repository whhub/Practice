import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Analyse1Component } from './analyse1.component';

describe('Analyse1Component', () => {
  let component: Analyse1Component;
  let fixture: ComponentFixture<Analyse1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Analyse1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Analyse1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
