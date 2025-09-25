import { ModuleService } from '@/services/module-service';
import { Component } from '@angular/core';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course-service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import {NgForOf, NgIf} from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-course-list-component',
   imports: [CommonModule, RouterModule],
  templateUrl: './course-list-component.html',
  styleUrl: './course-list-component.scss'
})
export class CourseListComponent {

  courses : Course[] = [];  //list of courses
  departmentId: number = 0; //department id to filter courses by
  selectedCourse : Course | null = null; //selected course

  constructor(private courseService: CourseService, private moduleService : ModuleService,
    private route : ActivatedRoute) { }

  ngOnInit(): void {
    //get department id from route parameters
    this.route.params.subscribe(params => {
      this.departmentId = Number(params['id']);
      console.log('Department ID from route:', this.departmentId);
      this.fetchCourses();
    });
  }

  fetchCourses(){
    this.courseService.getCoursesByDepartmentId(this.departmentId).subscribe(
      (data => {
        this.courses = data;
        console.log('Courses fetched:', this.courses);
      })
    ); 
  }

  selectCourse(course: Course): void {
    this.selectedCourse = course;
    // Fetch modules for the selected course
    this.moduleService.getModulesByCourseId(course.courseId).subscribe(
      (modules => {
        console.log('Modules for course', course.courseName, ':', modules);
        this.selectedCourse!.modules = modules; // Update selected course with modules
      })
    );
  } 


  getModulesBySemester(semester: number): any[] {
    if (!this.selectedCourse || !this.selectedCourse.modules) {
      return [];
    }
    return this.selectedCourse.modules.filter(module => module.moduleSemester === semester);
  }

}


