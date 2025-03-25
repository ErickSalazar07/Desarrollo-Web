import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarVeterinarioComponent } from './mostrar-veterinario.component';

describe('MostrarVeterinarioComponent', () => {
  let component: MostrarVeterinarioComponent;
  let fixture: ComponentFixture<MostrarVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MostrarVeterinarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostrarVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
