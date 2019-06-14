import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivesUploadComponent } from './archives-upload.component';

describe('ArchivesUploadComponent', () => {
  let component: ArchivesUploadComponent;
  let fixture: ComponentFixture<ArchivesUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArchivesUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivesUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
