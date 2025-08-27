import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { StudentService } from '../../services/student-service';
import { ActivatedRoute, RouteConfigLoadEnd } from '@angular/router';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-form-component',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule, FormsModule, RouterModule],
  templateUrl: './student-form-component.html',
  styleUrls: ['./student-form-component.scss']
})
export class StudentFormComponent {
  studentForm!: FormGroup;
  successMessage: string ="";
  errorMessage: string ="";
  isEditMode: boolean = false;
  studentId!: number;

  constructor(private fb: FormBuilder, private studentService: StudentService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() : void {
    // Initialize the form or fetch data if needed
    this.studentForm = this.fb.group({
      studentFirstName: ['', Validators.required],
      studentLastName: ['', Validators.required],
      studentEmail: ['', [Validators.required, Validators.email]],
      studentPhoneNumber: ['', Validators.required],
      studentAddress: ['', Validators.required],
      studentGender: ['', Validators.required],
      studentDateOfBirth: ['', Validators.required],
      studentEnrollmentDate: [new Date(), Validators.required],
      studentStatus: ['inactive', Validators.required]
    });

    //get student Id for editing student
    this.studentId = Number(this.route.snapshot.paramMap.get('studentId'));

    // Check if we are in edit mode
    if (this.studentId) {
      this.isEditMode = true;
      this.studentService.getStudentById(this.studentId).subscribe({
        next: (student) => {
          // Populate the form with the student data
          this.studentForm.patchValue(student);
        },
        error: (error) => {
          this.errorMessage = 'Failed to load student data.';
        }
      });
    }
  }


  onSubmit() : void {

    if (this.studentForm.invalid) {
      // Mark all fields so errors show immediately
      this.errorMessage = 'Failed to add student. Please fill all fields.';
      this.studentForm.markAllAsTouched();
      return; // stop if invalid
    }
    
    //checks if edit mode ; if not then we should add a new student
    if (!this.isEditMode) {

      // Call the service to add the student
      this.studentService.addStudent(this.studentForm.value).subscribe({
        next: () => {
          this.successMessage = 'Student added successfully!';
          this.errorMessage = "";
          this.studentForm.reset(); // this is important for success message to appear
        },
        error: (error) => {
          this.errorMessage = 'Failed to add student.';
          this.successMessage = "";
        }
      });
    }
    else {
      // Call the service to update the student
      this.studentService.updateStudent(this.studentId, this.studentForm.value).subscribe({
        next: () => {
          this.successMessage = 'Student updated successfully!';
          this.errorMessage = "";
          this.studentForm
        },
        error: (error) => {
          this.errorMessage = 'Failed to update student.';
          this.successMessage = "";
        }
      });
    }

}
}

  