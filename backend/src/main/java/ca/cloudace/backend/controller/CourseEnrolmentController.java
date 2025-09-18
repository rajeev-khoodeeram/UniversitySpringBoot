package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.model.CourseEnrolment;
import ca.cloudace.backend.repository.CourseRepository;
import ca.cloudace.backend.service.CourseEnrolmentService;

@RestController
@RequestMapping("/api/course-enrolments")
public class CourseEnrolmentController {

    private final CourseRepository courseRepository;

    private final CourseEnrolmentService courseEnrolmentService;

    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, CourseRepository courseRepository) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.courseRepository = courseRepository;
    }

    // Define endpoints for CRUD operations here
    @GetMapping
    public List<CourseEnrolment> getAllCourseEnrolments() {
        return courseEnrolmentService.getAllCourseEnrolments();
    }

    @GetMapping("/{id}")
    public CourseEnrolment getCourseEnrolmentById(@PathVariable Long id) {
        return courseEnrolmentService.getCourseEnrolmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseEnrolment(@RequestParam Long id) {
        courseEnrolmentService.deleteCourseEnrolment(id);
    }

    @PostMapping
    public CourseEnrolment createCourseEnrolment(@RequestBody CourseEnrolment courseEnrolment) {
        return courseEnrolmentService.saveCourseEnrolment(courseEnrolment);
    }

    @PutMapping("/{id}")
    public CourseEnrolment updateCourseEnrolment(@PathVariable Long id, @RequestBody CourseEnrolment courseEnrolment) {
        return courseEnrolmentService.updateCourseEnrolment(id, courseEnrolment);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseEnrolment> getCourseEnrolmentsByCourseId(@PathVariable Long courseId) {
        return courseEnrolmentService.getCourseEnrolmentsByCourse(courseRepository.findById(courseId).orElse(null));
    }

}
