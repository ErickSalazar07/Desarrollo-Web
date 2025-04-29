import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioClienteComponent } from './formulario-cliente.component';

describe('FormularioClienteComponent', () => {
  let component: FormularioClienteComponent;
  let fixture: ComponentFixture<FormularioClienteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormularioClienteComponent]
    });
    fixture = TestBed.createComponent(FormularioClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
