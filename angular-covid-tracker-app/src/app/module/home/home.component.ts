import { Component, OnInit } from '@angular/core';
import { ITS_JUST_ANGULAR } from '@angular/core/src/r3_symbols';
import { LocationStats } from 'src/app/model/location-stats';
import { TrackerServiceService } from 'src/app/service/tracker-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  stats: LocationStats[] = [];
  totalReports:number;
  totalDiffr: number;

  country: any;
  p: number = 1;

  constructor(private service: TrackerServiceService) { }

  ngOnInit(): void {
    this.getData();
  }

  getData() {

    let response = this.service.getData()
    response.subscribe((data: LocationStats[]) =>{
      this.stats = data;
      
      this.totalDiffr = 0;
      this.totalReports = 0;
      for (let v of this.stats) {
        this.totalDiffr += v.diffFromPrevDay
        this.totalReports += v.latestTotalCases
      }
    });
  }

  search() {
    if (this.country == "") {
      this.ngOnInit();
    } else {
      this.stats = this.stats.filter(res => {
        return res.country.toLocaleLowerCase().match(this.country.toLocaleLowerCase());
      })
      
      this.p = 1;
      this.totalDiffr = 0;
      this.totalReports = 0;
      for (let v of this.stats) {
        this.totalDiffr += v.diffFromPrevDay
        this.totalReports += v.latestTotalCases
      }
    }
  }


}
