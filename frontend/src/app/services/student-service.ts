import { Injectable } from '@angular/core';
import { Student } from '../models/student.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appConfigServer } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private students: Student[] = [];
  private apiUrl = appConfigServer.apiUrl;


  constructor(private http: HttpClient) { }

  /**
   * Fetches the list of students from the API.
   * @returns An observable containing the list of students.
   */
  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiUrl}/add`, student);
  }

  deleteStudent(studentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${studentId}`);
  }

  updateStudent(studentId: number, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${studentId}`, student);
  }

  getStudentById(studentId: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${studentId}`);
  }

}
