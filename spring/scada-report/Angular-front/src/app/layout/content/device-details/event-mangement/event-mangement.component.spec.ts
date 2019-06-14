import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventMangementComponent } from './event-mangement.component';

describe('EventMangementComponent', () => {
  let component: EventMangementComponent;
  let fixture: ComponentFixture<EventMangementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventMangementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventMangementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
