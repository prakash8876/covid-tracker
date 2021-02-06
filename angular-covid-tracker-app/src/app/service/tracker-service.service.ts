import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrackerServiceService {

  uri: string = "http://localhost:8080/getData"

  constructor(private http: HttpClient) { }

  getData() {
    return this.http.get(this.uri)
  }
}
