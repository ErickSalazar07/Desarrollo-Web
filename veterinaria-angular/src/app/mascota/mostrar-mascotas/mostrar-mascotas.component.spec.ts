import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarMascotasComponent } from './mostrar-mascotas.component';

describe('MostrarMascotasComponent', () => {
  let component: MostrarMascotasComponent;
  let fixture: ComponentFixture<MostrarMascotasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarMascotasComponent]
    });
    fixture = TestBed.createComponent(MostrarMascotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
