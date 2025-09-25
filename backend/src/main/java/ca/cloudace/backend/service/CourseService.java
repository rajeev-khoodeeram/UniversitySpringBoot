package ca.cloudace.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.controller.CourseDTO;
import ca.cloudace.backend.model.Course;
import ca.cloudace.backend.repository.CourseRepository;
import ca.cloudace.backend.repository.DepartmentRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    // Add service methods as needed

    public String getCourseDetails(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> "Course found: " + course.getCourseName())
                .orElse("Course not found");
    }

    public String getCourseName(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> course.getCourseName())
                .orElse("Course not found");
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public Course updateCourse(Long courseId, Course updatedCourse) {

        System.out.println("Updating course with ID: " + courseId);
        System.out.println("Updated course details: " + updatedCourse);
        return courseRepository.findById(courseId)
                .map(course -> {
                    course.setCourseName(updatedCourse.getCourseName());
                    course.setCourseDuration(updatedCourse.getCourseDuration());
                    course.setCourseLevel(updatedCourse.getCourseLevel());
                    course.setCourseAbbrev(updatedCourse.getCourseAbbrev());
                    course.setDepartment(updatedCourse.getDepartment());
                    return courseRepository.save(course);
                })
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream().map(
                c -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setCourseId(c.getCourseId());
                    dto.setCourseName(c.getCourseName());
                    dto.setCourseDuration(c.getCourseDuration());
                    dto.setCourseLevel(c.getCourseLevel());
                    dto.setCourseAbbrev(c.getCourseAbbrev());
                    if (c.getDepartment() != null) {
                        dto.setDepartmentName(c.getDepartment().getDepartmentName());
                    }
                    return dto;
                }).collect(Collectors.toList());

        return courses;
    }

    public Optional<CourseDTO> getCourseById(Long courseId) {
        return courseRepository.findById(courseId).map(c -> {
            CourseDTO dto = new CourseDTO();
            dto.setCourseId(c.getCourseId());
            dto.setCourseName(c.getCourseName());
            dto.setCourseDuration(c.getCourseDuration());
            dto.setCourseLevel(c.getCourseLevel());
            dto.setCourseAbbrev(c.getCourseAbbrev());
            if (c.getDepartment() != null) {
                dto.setDepartmentName(c.getDepartment().getDepartmentName());
            }
            return dto;
        });

    }

    public boolean courseExists(Long courseId) {
        return courseRepository.existsById(courseId);
    }

    /**
     * Count total number of courses (this can be use for pagination, displaying on
     * frontend, etc.)
     * 
     * @return
     */
    public long countCourses() {
        return courseRepository.count(); // is catered by JpaRepository automatically for us !!
    }

    public List<Course> findCoursesByDepartmentId(Long departmentId) {
        return courseRepository.findByDepartment(departmentRepository.findById(departmentId).orElse(null));
    }

}
