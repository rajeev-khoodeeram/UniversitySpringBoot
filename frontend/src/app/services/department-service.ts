import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Department } from '../models/department';
import { apiBaseUrl } from 'src/app.config';
import { OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})

export class DepartmentService implements OnInit {
  facultyId!: number | null;
  departments: Department[] = [];

  private apiUrl = `${apiBaseUrl}/departments`;

  constructor(private http: HttpClient, private authService: AuthService) { }

  getDepartmentsByFacultyId(facultyId: number): Observable<Department[]> {
    return this.http.get<Department[]>(`${this.apiUrl}/faculty/${facultyId}`, 
       {   
        headers: new HttpHeaders({
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
  });
  }

  ngOnInit() {
    this.getDepartmentsByFacultyId(1).subscribe(departments => {
      console.log('Departments for Faculty ID 1:', departments);
      this.departments = departments;
    }
    );
  }
  
}
