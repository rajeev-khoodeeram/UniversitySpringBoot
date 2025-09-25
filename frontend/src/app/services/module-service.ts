import { Injectable } from '@angular/core';
import { OnInit } from '@angular/core';
import { apiBaseUrl } from 'src/app.config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Module } from '../models/module';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class ModuleService  implements OnInit   {

  private apiUrl = `${apiBaseUrl}/modules`; // Base URL for the API
  courseId: number = 0;

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  
  getModulesByCourseId(courseId: number): Observable<Module[]> {
    const url = `${this.apiUrl}/course/${courseId}`;
    return this.http.get<Module[]>(url
      , {   
   headers: new HttpHeaders({
    'Authorization': `Bearer ${this.authService.getToken()}`
    })
    }
    );
  }
  
}
