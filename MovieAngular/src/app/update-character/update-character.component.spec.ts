import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCharacterComponent } from './update-character.component';

describe('UpdateCharacterComponent', () => {
  let component: UpdateCharacterComponent;
  let fixture: ComponentFixture<UpdateCharacterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateCharacterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateCharacterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
