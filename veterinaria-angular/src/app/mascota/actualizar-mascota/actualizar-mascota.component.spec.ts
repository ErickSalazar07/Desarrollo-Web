import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarMascotaComponent } from './actualizar-mascota.component';

describe('ActualizarMascotaComponent', () => {
  let component: ActualizarMascotaComponent;
  let fixture: ComponentFixture<ActualizarMascotaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActualizarMascotaComponent]
    });
    fixture = TestBed.createComponent(ActualizarMascotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
