import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearVeterinarioComponent } from './crear-veterinario.component';

describe('CrearVeterinarioComponent', () => {
  let component: CrearVeterinarioComponent;
  let fixture: ComponentFixture<CrearVeterinarioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrearVeterinarioComponent]
    });
    fixture = TestBed.createComponent(CrearVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
