import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeyboardCartComponent } from './keyboard-cart.component';

describe('KeyboardCartComponent', () => {
  let component: KeyboardCartComponent;
  let fixture: ComponentFixture<KeyboardCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KeyboardCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KeyboardCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
