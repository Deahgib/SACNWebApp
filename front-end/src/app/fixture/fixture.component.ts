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
  fixtures = [
    new Fixture(0, "light1"),
    new Fixture(1, "light2"),
    new Fixture(2, "light3"),
    new Fixture(3, "light4"),
    new Fixture(4, "light5"),
    new Fixture(5, "light6"),
    new Fixture(6, "light7"),
    new Fixture(7, "light8"),
    new Fixture(8, "light9")
  ];

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
