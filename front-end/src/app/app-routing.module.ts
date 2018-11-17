import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FixtureComponent } from './fixture/fixture.component';
import { MemoriesComponent } from './memories/memories.component';
import { NewComponent } from './memories/new/new.component';
import { LiveComponent } from './memories/live/live.component';
import { PatchComponent } from './patch/patch.component';
import { AddressesComponent } from './patch/addresses/addresses.component';
import { DmxComponent } from './patch/dmx/dmx.component';

const routes: Routes = [
  { path: 'lights', component: FixtureComponent },
  { path: 'memories', component: MemoriesComponent,
  children: [
    { path: '', redirectTo: 'live' , pathMatch: 'full'},
    { path: 'live', component: LiveComponent },
    { path: 'new', component: NewComponent }
  ]},
  { path: 'patch', component: PatchComponent ,
    children: [
      { path: '', redirectTo: 'addresses' , pathMatch: 'full'},
      { path: 'addresses', component: AddressesComponent },
      { path: 'dmx', component: DmxComponent }
    ]}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash:true,enableTracing:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
