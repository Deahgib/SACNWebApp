import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { MatSliderModule } from '@angular/material/slider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FixtureComponent } from './fixture/fixture.component';
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
    MatTabsModule,
    MatSidenavModule,
    MatToolbarModule,
    HttpClientModule
  ],
  exports: [MatSliderModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  appName = "Light Controll Testing"
}
