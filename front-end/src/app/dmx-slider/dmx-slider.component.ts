import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dmx-slider',
  templateUrl: './dmx-slider.component.html',
  styleUrls: ['./dmx-slider.component.css']
})
export class DmxSliderComponent implements OnInit {
  identity = "Empty";
  disabled = false;
  invert = false;
  max = 255;
  min = 0;
  step = 1;
  thumbLabel = false;
  value = 0;
  vertical = false;

  onInputChange(event: any) {
    console.log("This is emitted as the thumb slides");
  }

  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

}
