package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Custom query methods (if needed) can be defined here
    // but most of them are implemented automatically; no need to write sql !!

  

}
