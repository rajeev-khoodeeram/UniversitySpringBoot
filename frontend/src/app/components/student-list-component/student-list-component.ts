import { Component } from '@angular/core';
import { Student } from '../../models/student.model';
import { StudentService} from '../../services/student-service';
import { OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-student-list-component',
  imports: [CommonModule],
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
    this.studentService.getStudents().subscribe((data) => {
      this.students = data;
    }, (error) => {
      console.error('Error fetching students:', error);
    });
  }

  editStudent(student: Student) {
    // Navigate to the student form with the student data
    this.router.navigate(['/students/edit', student.studentId]);
  }

  deleteStudent(studentId: number) {

    if (confirm('Are you sure you want to delete this student?')) {
      this.studentService.deleteStudent(studentId).subscribe({
        next: () => {
          this.students = this.students.filter(s => s.studentId !== studentId);
        },
        error: (error) => {
          console.error('Error deleting student:', error);
        }
      });
    }
  }

}
