import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarValorDineroComponent } from './mostrar-valor-dinero.component';

describe('MostrarValorDineroComponent', () => {
  let component: MostrarValorDineroComponent;
  let fixture: ComponentFixture<MostrarValorDineroComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarValorDineroComponent]
    });
    fixture = TestBed.createComponent(MostrarValorDineroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
