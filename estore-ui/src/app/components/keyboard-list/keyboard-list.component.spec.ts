import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeyboardListComponent } from './keyboard-list.component';

describe('KeyboardListComponent', () => {
  let component: KeyboardListComponent;
  let fixture: ComponentFixture<KeyboardListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KeyboardListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KeyboardListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
