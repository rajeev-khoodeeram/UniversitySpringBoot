import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OnInit } from '@angular/core';
import { FacultyService } from '../../services/faculty-service';
import { Faculty } from '../../models/faculty';
import { Router } from '@angular/router';

@Component({
  selector: 'app-faculty-list-component',
  imports: [CommonModule, RouterModule],
  templateUrl: './faculty-list-component.html',
  styleUrl: './faculty-list-component.scss'
})


export class FacultyListComponent implements OnInit   {
  faculties: any[] = [];
  
  constructor(private facultyService: FacultyService, private router: Router) { }


  ngOnInit() {
  this.loadFaculties(); 
  }

  loadFaculties() { 
    //calls faculty service to get list of faculties
    this.facultyService.getFaculties().subscribe((data: any[]) => {
      this.faculties = data;
      console.log('Faculties loaded:', this.faculties);
    }, error => {
      console.error('Error loading faculties:', error);
    });

  }


}
