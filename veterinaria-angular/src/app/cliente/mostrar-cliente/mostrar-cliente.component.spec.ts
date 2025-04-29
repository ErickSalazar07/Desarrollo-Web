import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarClienteComponent } from './mostrar-cliente.component';

describe('MostrarClienteComponent', () => {
  let component: MostrarClienteComponent;
  let fixture: ComponentFixture<MostrarClienteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarClienteComponent]
    });
    fixture = TestBed.createComponent(MostrarClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
