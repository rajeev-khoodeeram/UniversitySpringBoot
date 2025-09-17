package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;   

import ca.cloudace.backend.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    

}
