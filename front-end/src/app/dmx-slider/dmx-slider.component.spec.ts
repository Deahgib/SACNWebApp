import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DmxSliderComponent } from './dmx-slider.component';

describe('DmxSliderComponent', () => {
  let component: DmxSliderComponent;
  let fixture: ComponentFixture<DmxSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DmxSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DmxSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
