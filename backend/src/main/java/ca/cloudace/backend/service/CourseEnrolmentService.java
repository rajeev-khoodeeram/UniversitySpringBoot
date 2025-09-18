package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Course;
import ca.cloudace.backend.model.CourseEnrolment;
import ca.cloudace.backend.repository.CourseEnrolmentRepository;

@Service
public class CourseEnrolmentService {

    private final CourseEnrolmentRepository courseEnrolmentRepository;

    public CourseEnrolmentService(CourseEnrolmentRepository courseEnrolmentRepository) {
        this.courseEnrolmentRepository = courseEnrolmentRepository;
    }

    // Add service methods here as needed
    public List<CourseEnrolment> getAllCourseEnrolments() {
        return courseEnrolmentRepository.findAll();
    }

    public CourseEnrolment getCourseEnrolmentById(Long id) {
        return courseEnrolmentRepository.findById(id).orElse(null);
    }

    public CourseEnrolment saveCourseEnrolment(CourseEnrolment courseEnrolment) {
        return courseEnrolmentRepository.save(courseEnrolment);
    }

    public void deleteCourseEnrolment(Long id) {
        courseEnrolmentRepository.deleteById(id);
    }

    public CourseEnrolment updateCourseEnrolment(Long id, CourseEnrolment updatedCourseEnrolment) {
        return courseEnrolmentRepository.findById(id)
                .map(courseEnrolment -> {
                    courseEnrolment.setStudent(updatedCourseEnrolment.getStudent());
                    courseEnrolment.setCourse(updatedCourseEnrolment.getCourse());
                    courseEnrolment.setCourseEnrolDate(updatedCourseEnrolment.getCourseEnrolDate());
                    courseEnrolment.setCourseEnrolStatus(updatedCourseEnrolment.getCourseEnrolStatus());
                    courseEnrolment.setCourseEnrolGraduation(updatedCourseEnrolment.getCourseEnrolGraduation());
                    return courseEnrolmentRepository.save(courseEnrolment);
                })
                .orElse(null);
    }

    public List<CourseEnrolment> getCourseEnrolmentsByCourse(Course course) {
        return courseEnrolmentRepository.findByCourse(course);
    }

}
