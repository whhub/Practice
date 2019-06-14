import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProduceStatusComponent } from './produce-status.component';

describe('ProduceStatusComponent', () => {
  let component: ProduceStatusComponent;
  let fixture: ComponentFixture<ProduceStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProduceStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProduceStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
