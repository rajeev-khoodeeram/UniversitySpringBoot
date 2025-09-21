import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Faculty } from '../../models/faculty'; // Import Faculty model
import { FacultyService } from '../../services/faculty-service'; // Import Faculty service
import { ActivatedRoute } from '@angular/router';
import { RippleModule } from 'primeng/ripple';
import { StyleClassModule } from  'primeng/styleclass';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { TopbarWidget } from '../../pages/landing/components/topbarwidget.component';
import { HeroWidget } from '../../pages/landing/components/herowidget';
import { FeaturesWidget } from '../../pages/landing/components/featureswidget';
import { HighlightsWidget } from '../../pages/landing/components/highlightswidget';
import { PricingWidget } from '../../pages/landing/components/pricingwidget';
import { FooterWidget } from '../../pages/landing/components/footerwidget';

@Component({
  selector: 'app-faculty-details-component',
  imports: [CommonModule, RouterModule, TopbarWidget, HeroWidget, FeaturesWidget, HighlightsWidget, PricingWidget, FooterWidget, RippleModule, StyleClassModule, ButtonModule, DividerModule],
  template:    ` 
        <div class="bg-surface-0 dark:bg-surface-900">
            <div id="home" class="landing-wrapper overflow-hidden">
                <topbar-widget class="py-6 px-6 mx-0 md:mx-12 lg:mx-20 lg:px-20 flex items-center justify-between relative lg:static" />
                <hero-widget />
              <div>
  
  <div *ngIf="faculty; else noFaculty">
    <h2>Welcome to the Faculty of {{ faculty.facultyName }}</h2>    
    <p><strong>Faculty Code:</strong> {{ faculty.facultyCode }}</p>
    <p><strong>Faculty Phone:</strong> {{ faculty.facultyPhone }}</p>
    <p><strong>Faculty Dean:</strong> {{ faculty.facultyDean.lecturerLastName }} {{faculty.facultyDean.lecturerFirstName}}</p>
    <!-- Add more fields as necessary -->
  </div>

  <h1>List of departments</h1>
  
  
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

  constructor(private facultyService: FacultyService, private route: ActivatedRoute) {
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
      },
      error => {
        console.error('Error fetching faculty details:', error);
      }
    );
    console.log('Faculty ID:', id);
  } 

}
