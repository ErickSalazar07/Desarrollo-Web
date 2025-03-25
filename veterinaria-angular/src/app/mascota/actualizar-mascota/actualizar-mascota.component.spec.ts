import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarMascotaComponent } from './actualizar-mascota.component';

describe('ActualizarMascotaComponent', () => {
  let component: ActualizarMascotaComponent;
  let fixture: ComponentFixture<ActualizarMascotaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarMascotaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActualizarMascotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
