import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarTratamientoComponent } from './asignar-tratamiento.component';

describe('AsignarTratamientoComponent', () => {
  let component: AsignarTratamientoComponent;
  let fixture: ComponentFixture<AsignarTratamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AsignarTratamientoComponent]
    });
    fixture = TestBed.createComponent(AsignarTratamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
