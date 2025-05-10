import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarKpisComponent } from './mostrar-kpis.component';

describe('MostrarKpisComponent', () => {
  let component: MostrarKpisComponent;
  let fixture: ComponentFixture<MostrarKpisComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarKpisComponent]
    });
    fixture = TestBed.createComponent(MostrarKpisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
