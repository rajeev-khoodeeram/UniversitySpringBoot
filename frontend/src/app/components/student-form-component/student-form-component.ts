import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { StudentService } from '../../services/student-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-form-component',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './student-form-component.html',
  styleUrl: './student-form-component.scss'
})
export class StudentFormComponent {
  studentForm!: FormGroup;
  student: any = {}; // for form binding
  successMessage: string = ""
  errorMessage: string = '';
 studentId!: number | null;
  isEditMode: boolean = false;
  route: any;

  constructor(private fb: FormBuilder,  private studentService : StudentService,
    private router: Router) {}

  ngOnInit(): void {
    
    const id = this.route.snapshot.paramMap.get('id');
    this.studentId = id ? Number(id) : null;
    this.isEditMode = !!this.studentId;

    // Fetch the student data if in edit mode
    if (this.studentId) {
      this.isEditMode = true;
      this.studentService.getStudentById(this.studentId).subscribe(data => {
        this.student = data; // prefill form in edit mode
      });
    }

    // Initialize the form here
    this.studentForm = this.fb.group({
      studentFirstName: ['', Validators.required],
      studentLastName: ['', Validators.required],
      studentEmail: ['', [Validators.required, Validators.email]]
    });
  }


  onSubmit() : void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.studentForm.invalid) {
      this.errorMessage = 'Please fill out all required fields correctly.';
      return;
    }

    this.studentService.addStudent(this.studentForm.value).subscribe({
      next: () => {
        this.successMessage = 'Student added successfully!';
        this.studentForm.reset(); // reset form after success
      },
      error: () => {
        this.errorMessage = 'Something went wrong while saving the student. Please try again.';
      }
    });
  } 
}