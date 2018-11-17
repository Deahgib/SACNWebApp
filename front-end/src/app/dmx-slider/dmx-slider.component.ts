import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dmx-slider',
  templateUrl: './dmx-slider.component.html',
  styleUrls: ['./dmx-slider.component.css']
})
export class DmxSliderComponent implements OnInit {
  @Input() identity: any;
  disabled = false;
  invert = false;
  max = 255;
  min = 0;
  step = 1;
  thumbLabel = false;
  //value = 0;
  vertical = false;

  onInputChange(event: any) {
    console.log("This is emitted as the thumb slides " + event);
    //this.value = event
      const postedData = { channel: 0, key: this.identity.key, value: event };
      this.http.post('/api/patch/fixture/value', postedData).subscribe(result => {
        console.log(result);
      }, error => console.log('There was an error: '));
  }

  constructor(private http: HttpClient) {}

  ngOnInit() {
  }

}
