import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';



@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule, FormsModule, HttpClientModule, Ng2SearchPipeModule, NgxPaginationModule
  ], exports: [HomeComponent]
})
export class TrackerModuleModule { }
