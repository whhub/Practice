import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulkAnalysisComponent } from './bulk-analysis.component';

describe('BulkAnalysisComponent', () => {
  let component: BulkAnalysisComponent;
  let fixture: ComponentFixture<BulkAnalysisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulkAnalysisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulkAnalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
