package ca.cloudace.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Course;
import ca.cloudace.backend.model.CourseEnrolment;

@Repository
public interface CourseEnrolmentRepository extends JpaRepository<CourseEnrolment, Long> {

    // Custom query method to find enrolments by course ID
    List<CourseEnrolment> findByCourse(Course course);
}
