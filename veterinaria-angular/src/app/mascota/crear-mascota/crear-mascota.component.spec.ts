import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearMascotaComponent } from './crear-mascota.component';

describe('CrearMascotaComponent', () => {
  let component: CrearMascotaComponent;
  let fixture: ComponentFixture<CrearMascotaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrearMascotaComponent]
    });
    fixture = TestBed.createComponent(CrearMascotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
