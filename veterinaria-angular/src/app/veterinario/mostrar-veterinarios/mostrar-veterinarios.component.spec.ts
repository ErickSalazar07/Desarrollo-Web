import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarVeterinariosComponent } from './mostrar-veterinarios.component';

describe('MostrarVeterinariosComponent', () => {
  let component: MostrarVeterinariosComponent;
  let fixture: ComponentFixture<MostrarVeterinariosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarVeterinariosComponent]
    });
    fixture = TestBed.createComponent(MostrarVeterinariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
