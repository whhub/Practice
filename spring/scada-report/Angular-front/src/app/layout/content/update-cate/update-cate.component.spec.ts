import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCateComponent } from './update-cate.component';

describe('UpdateCateComponent', () => {
  let component: UpdateCateComponent;
  let fixture: ComponentFixture<UpdateCateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
