import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarVeterinarioComponent } from './actualizar-veterinario.component';

describe('ActualizarVeterinarioComponent', () => {
  let component: ActualizarVeterinarioComponent;
  let fixture: ComponentFixture<ActualizarVeterinarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActualizarVeterinarioComponent]
    });
    fixture = TestBed.createComponent(ActualizarVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
