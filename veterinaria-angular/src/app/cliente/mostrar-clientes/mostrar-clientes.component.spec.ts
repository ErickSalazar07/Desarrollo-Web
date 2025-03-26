import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarClientesComponent } from './mostrar-clientes.component';

describe('MostrarClientesComponent', () => {
  let component: MostrarClientesComponent;
  let fixture: ComponentFixture<MostrarClientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MostrarClientesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarClientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
