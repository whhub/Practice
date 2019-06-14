import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTypePageComponent } from './add-type-page.component';

describe('AddTypePageComponent', () => {
  let component: AddTypePageComponent;
  let fixture: ComponentFixture<AddTypePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTypePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTypePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
