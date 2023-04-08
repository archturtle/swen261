import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeyboardConfiguratorComponent } from './keyboard-configurator.component';

describe('KeyboardConfiguratorComponent', () => {
  let component: KeyboardConfiguratorComponent;
  let fixture: ComponentFixture<KeyboardConfiguratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KeyboardConfiguratorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KeyboardConfiguratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
