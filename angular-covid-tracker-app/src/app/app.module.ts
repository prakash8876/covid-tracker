import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './module/home/home.component';
import { TrackerModuleModule } from './module/tracker-module/tracker-module.module';
import { TrackerServiceService } from './service/tracker-service.service';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, TrackerModuleModule, Ng2SearchPipeModule, NgxPaginationModule
  ],
  providers: [TrackerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
