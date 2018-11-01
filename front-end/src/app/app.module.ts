import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { FixtureComponent } from './fixture/fixture.component';
import { HttpClientModule } from '@angular/common/http';
import { DmxSliderComponent } from './dmx-slider/dmx-slider.component';

@NgModule({
  declarations: [
    AppComponent,
    FixtureComponent,
    DmxSliderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    HttpClientModule
  ],
  exports: [MatSliderModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  appName = "Light Controll Testing"
}
