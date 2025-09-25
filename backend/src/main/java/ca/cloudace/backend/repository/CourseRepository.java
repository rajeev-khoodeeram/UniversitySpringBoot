package ca.cloudace.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Course;
import ca.cloudace.backend.model.Department;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByDepartment(Department department);

}
