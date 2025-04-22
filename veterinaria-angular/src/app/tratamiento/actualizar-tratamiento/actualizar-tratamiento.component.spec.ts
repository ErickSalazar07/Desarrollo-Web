import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarTratamientoComponent } from './actualizar-tratamiento.component';

describe('ActualizarTratamientoComponent', () => {
  let component: ActualizarTratamientoComponent;
  let fixture: ComponentFixture<ActualizarTratamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActualizarTratamientoComponent]
    });
    fixture = TestBed.createComponent(ActualizarTratamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
