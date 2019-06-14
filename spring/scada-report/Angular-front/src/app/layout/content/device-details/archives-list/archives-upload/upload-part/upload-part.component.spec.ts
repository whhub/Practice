import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadPartComponent } from './upload-part.component';

describe('UploadPartComponent', () => {
  let component: UploadPartComponent;
  let fixture: ComponentFixture<UploadPartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadPartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
