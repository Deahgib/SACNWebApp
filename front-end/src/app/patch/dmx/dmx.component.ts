import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dmx',
  templateUrl: './dmx.component.html',
  styleUrls: ['./dmx.component.css']
})
export class DmxComponent implements OnInit {

dmxPatch = []

constructor(private http: HttpClient) {}
  ngOnInit() {
    console.log("Fiture Loaded")
     this.http.get('/api/patch')
        .subscribe(data => {
            this.dmxPatch = data as any [];
            console.log(data)
        });
  }

}
