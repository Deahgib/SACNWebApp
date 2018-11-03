import { Component, OnInit } from '@angular/core';
import { Fixture } from './fixture';
import { HttpClient } from '@angular/common/http';
import { Patch } from '../patch';


@Component({
  selector: 'app-fixture',
  templateUrl: './fixture.component.html',
  styleUrls: ['./fixture.component.css']
})
export class FixtureComponent implements OnInit {
  patch = new Patch();

  constructor(private http: HttpClient) {}
  ngOnInit() {
    console.log("Fiture Loaded")
     this.http.get('/api/patch/fixtures')
        .subscribe(data => {
            this.patch.fixtures = data as any [];
            console.log(data)
        });
  }

}
