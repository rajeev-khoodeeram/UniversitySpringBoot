import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OnInit } from '@angular/core';
import { Course } from '../models/course';
import { apiBaseUrl } from 'src/app.config';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService implements OnInit  {
  private apiUrl = `${apiBaseUrl}/courses`; // Base URL for the API

  constructor(private http: HttpClient, private authService: AuthService) { }


  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getCoursesByDepartmentId(departmentId: number): Observable<Course[]> {
    const url = `${this.apiUrl}/dept/${departmentId}`;
    return this.http.get<Course[]>(url
      , {   
   headers: new HttpHeaders({
    'Authorization': `Bearer ${this.authService.getToken()}`    
    })}
    );
  }

  getCourseById(courseId: number): Observable<Course> {
    const url = `${this.apiUrl}/${courseId}`;
    return this.http.get<Course>(url);
  }

  
}
