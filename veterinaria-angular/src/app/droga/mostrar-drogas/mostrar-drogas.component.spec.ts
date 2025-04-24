import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarDrogasComponent } from './mostrar-drogas.component';

describe('MostrarDrogasComponent', () => {
  let component: MostrarDrogasComponent;
  let fixture: ComponentFixture<MostrarDrogasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarDrogasComponent]
    });
    fixture = TestBed.createComponent(MostrarDrogasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
