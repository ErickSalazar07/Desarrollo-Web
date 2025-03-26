import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarMascotasComponent } from './mostrar-mascotas.component';

describe('MostrarMascotasComponent', () => {
  let component: MostrarMascotasComponent;
  let fixture: ComponentFixture<MostrarMascotasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MostrarMascotasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarMascotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
