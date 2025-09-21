import { ChangeDetectorRef, Component } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService} from '../../services/student-service';
import { OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-student-list-component',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './student-list-component.html',
  styleUrl: './student-list-component.scss'
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];
  router: any;

  constructor(private studentService: StudentService) {}

  ngOnInit() {
    this.fetchStudents();
  }

  fetchStudents() {
    this.studentService.getStudents().subscribe({
      next: (data) => {
        this.students = data;
        console.log('Students fetched successfully:', data);
        
      },
      error: (error) => {
        console.error('Error fetching students:', error);
      }
    });
  }

    /**
     * Deletes a student by ID.
     * @param studentId The ID of the student to delete.
     */
    deleteStudent(studentId: number) {
      const confirmed = confirm('Are you sure you want to delete this student?');
      if (confirmed) {
        console.log('Deleting student with ID:', studentId);
        this.studentService.deleteStudent(studentId).subscribe({
          next: () => {
            console.log('Student deleted successfully');
            this.students = this.students.filter(s => s.studentId !== studentId);
          },
          error: (error) => {
            console.error('Error deleting student:', error);
          }
        });
      }
    }
  
}