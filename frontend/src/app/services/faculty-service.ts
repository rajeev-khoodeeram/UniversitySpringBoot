import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { apiBaseUrl } from '../../app.config';
import { Observable } from 'rxjs';
import { Faculty } from '@/models/faculty';


@Injectable({
  providedIn: 'root'
})

export class FacultyService {
  private api = apiBaseUrl + "/faculty" // Endpoint to get list of faculties

  constructor(private http: HttpClient) {}

  getFaculties() {
    return this.http.get<Faculty[]>(this.api);
  }
}
