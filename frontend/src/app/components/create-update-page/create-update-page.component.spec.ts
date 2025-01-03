import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateUpdatePageComponent } from './create-update-page.component';

describe('CreateUpdatePageComponent', () => {
  let component: CreateUpdatePageComponent;
  let fixture: ComponentFixture<CreateUpdatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateUpdatePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateUpdatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
