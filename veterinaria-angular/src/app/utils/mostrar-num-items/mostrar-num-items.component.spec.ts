import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostrarNumItemsComponent } from './mostrar-num-items.component';

describe('MostrarNumItemsComponent', () => {
  let component: MostrarNumItemsComponent;
  let fixture: ComponentFixture<MostrarNumItemsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostrarNumItemsComponent]
    });
    fixture = TestBed.createComponent(MostrarNumItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
