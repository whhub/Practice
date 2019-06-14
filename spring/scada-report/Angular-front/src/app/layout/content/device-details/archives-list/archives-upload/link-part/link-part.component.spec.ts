import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LinkPartComponent } from './link-part.component';

describe('LinkPartComponent', () => {
  let component: LinkPartComponent;
  let fixture: ComponentFixture<LinkPartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LinkPartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LinkPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
