import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlgorithmModelComponent } from './algorithm-model.component';

describe('AlgorithmModelComponent', () => {
  let component: AlgorithmModelComponent;
  let fixture: ComponentFixture<AlgorithmModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlgorithmModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlgorithmModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
