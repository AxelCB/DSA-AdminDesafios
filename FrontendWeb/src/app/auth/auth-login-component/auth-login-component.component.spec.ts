import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthLoginComponentComponent } from './auth-login-component.component';

describe('AuthLoginComponentComponent', () => {
  let component: AuthLoginComponentComponent;
  let fixture: ComponentFixture<AuthLoginComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthLoginComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthLoginComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
