import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileMangementComponent } from './file-mangement.component';

describe('FileMangementComponent', () => {
  let component: FileMangementComponent;
  let fixture: ComponentFixture<FileMangementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileMangementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileMangementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
