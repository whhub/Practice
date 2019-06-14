import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileHomeComponent } from './file-home.component';

describe('FileHomeComponent', () => {
  let component: FileHomeComponent;
  let fixture: ComponentFixture<FileHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
