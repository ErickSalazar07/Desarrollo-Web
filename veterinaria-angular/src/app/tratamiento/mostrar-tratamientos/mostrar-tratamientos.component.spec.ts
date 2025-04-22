import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarTratamientosComponent } from './mostrar-tratamientos.component';

describe('MostrarTratamientosComponent', () => {
  let component: MostrarTratamientosComponent;
  let fixture: ComponentFixture<MostrarTratamientosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarTratamientosComponent]
    });
    fixture = TestBed.createComponent(MostrarTratamientosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
