import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardVeterinarioComponent } from './dashboard-veterinario.component';

describe('DashboardVeterinarioComponent', () => {
  let component: DashboardVeterinarioComponent;
  let fixture: ComponentFixture<DashboardVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardVeterinarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
