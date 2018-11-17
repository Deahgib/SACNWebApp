import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DmxComponent } from './dmx.component';

describe('DmxComponent', () => {
  let component: DmxComponent;
  let fixture: ComponentFixture<DmxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DmxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DmxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
