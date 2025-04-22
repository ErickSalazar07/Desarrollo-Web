import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarListaItemsComponent } from './mostrar-lista-items.component';

describe('MostrarListaItemsComponent', () => {
  let component: MostrarListaItemsComponent;
  let fixture: ComponentFixture<MostrarListaItemsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarListaItemsComponent]
    });
    fixture = TestBed.createComponent(MostrarListaItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
