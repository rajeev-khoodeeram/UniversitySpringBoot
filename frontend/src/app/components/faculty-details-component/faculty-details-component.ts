import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Faculty } from '../../models/faculty'; // Import Faculty model
import { Department } from '../../models/department';
import { FacultyService } from '../../services/faculty-service'; // Import Faculty service
import { ActivatedRoute } from '@angular/router';
import { RippleModule } from 'primeng/ripple';
import { StyleClassModule } from  'primeng/styleclass';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { TopbarWidget } from '../../pages/landing/components/topbarwidget.component';
import { HeroWidget } from '../../pages/landing/components/herowidget';
import { FooterWidget } from '../../pages/landing/components/footerwidget';
import { DepartmentService } from '../../services/department-service';

@Component({
  selector: 'app-faculty-details-component',
  imports: [CommonModule, RouterModule, TopbarWidget, HeroWidget, FooterWidget, RippleModule, StyleClassModule, ButtonModule, DividerModule],
  template:    ` 
        <div class="bg-surface-0 dark:bg-surface-900">
            <div id="home" class="landing-wrapper overflow-hidden">
                <topbar-widget class="py-6 px-6 mx-0 md:mx-12 lg:mx-20 lg:px-20 flex items-center justify-between relative lg:static" />
                <hero-widget />
              
<!-- faculty.html -->
<div class="faculty-wrap" *ngIf="faculty; else noFaculty">
  <article class="faculty-card" aria-labelledby="faculty-title">
    <header class="faculty-header">
      <div class="title">
        <h1 id="faculty-title">Welcome to the Faculty of {{ faculty.facultyName }}</h1>
        <p class="subtitle">Delivering excellence in computing, engineering and digital services.</p>
      </div>

      <div class="badges" aria-hidden="true">
        <div class="badge">Faculty Code: <strong style="margin-left:0.5rem">{{ faculty.facultyCode }}</strong></div>
      </div>
    </header>

    <section class="faculty-info" role="list">
      <div class="info-item" role="listitem">
        <div class="label">Faculty Phone</div>
        <div class="value">{{ faculty.facultyPhone }}</div>
      </div>
      <div class="info-item" role="listitem">
        <div class="label">Faculty Dean</div>
        <div class="value">{{ faculty.facultyDean.lecturerLastName }} {{faculty.facultyDean.lecturerFirstName}}</div>
      </div>
      <div class="info-item" role="listitem">
        <div class="label">Contact Email</div>
        <div class="value">{{faculty.facultyEmail}}</div>
      </div>
    </section>

    <section class="departments" aria-labelledby="dept-title">
      <div class="section-title" id="dept-title">List of departments</div>

      <!-- Table for wider screens -->
      <table class="dept-table" role="table" aria-describedby="dept-desc">
        <caption id="dept-desc" style="display:none">Department names and codes</caption>
        <thead>
          <tr>
            <th scope="col">Department Name</th>
            <th scope="col" [colSpan]="2">Department Code</th>
          </tr>
        </thead>
        <tbody>
         <tr *ngFor="let department of departments" >
          <td>{{ department.departmentName }}</td>
          <td>{{ department.departmentCode }}</td>
          <td><a [routerLink]="['/courses/dept', department.departmentId]" class="p-button p-component p-button-text p-button-plain">View Courses >></a></td>
        </tr>
        </tbody>
      </table>

      <!-- Stacked list for small screens -->
      <ul class="dept-list" aria-hidden="true">
        <li><span class="name">Applied Computer Science &amp; Engg</span><span class="code">ACS</span></li>
        <li><span class="name">Computer Science</span><span class="code">ICT-CS</span></li>
        <li><span class="name">Software Engineering</span><span class="code">ICT-SE</span></li>
        <li><span class="name">Data Science &amp; AI</span><span class="code">ICT-DS</span></li>
        <li><span class="name">Networking &amp; Cybersecurity</span><span class="code">ICT-NET</span></li>
      </ul>
    </section>
  </article>
    <ng-template #noFaculty>
    <p>Faculty not found.</p>
  </ng-template>
</div>


  <ng-template #noFaculty>
    <p>Faculty not found.</p>
  </ng-template>

              
                <footer-widget />
            </div>
        </div>
    `,
  styleUrl: './faculty-details-component.scss'
})
export class FacultyDetailsComponent {
  faculty: Faculty | null = null; // Define faculty property to hold faculty details
  facultyId: number | null = null; // Define facultyId property to
  departments: Department[] = [];

  constructor(private facultyService: FacultyService, private route: ActivatedRoute, private departmentService: DepartmentService ) {
    this.route.paramMap.subscribe(params => {
      this.facultyId = Number(params.get('id')); // Get faculty ID from route parameters
      console.log('Route param id:', this.facultyId)
      if (this.facultyId) {
        this.loadFacultyDetails(this.facultyId); // Load faculty details if ID is present
      }
    }
    );
  }

  loadFacultyDetails(id: number) {
    this.facultyService.getFaculties().subscribe(
      faculties => {
        this.faculty = faculties.find(faculty => faculty.facultyId === id) || null; // Find faculty by ID
        this.loadDepartments(id);
      },
      error => {
        console.error('Error fetching faculty details:', error);
      }
    );
    console.log('Faculty ID:', id);
  } 


  loadDepartments(facultyId: number) {
    this.departmentService.getDepartmentsByFacultyId(facultyId).subscribe(
      departments => {
        this.departments = departments;
        console.log('Departments:', this.departments);
      },
      error => {
        console.error('Error fetching departments:', error);
      }
    );
  }

}
