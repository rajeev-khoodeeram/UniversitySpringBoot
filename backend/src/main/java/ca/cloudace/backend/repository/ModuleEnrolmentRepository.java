package ca.cloudace.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Module;
import ca.cloudace.backend.model.ModuleEnrolment;
import ca.cloudace.backend.model.Student;

@Repository
public interface ModuleEnrolmentRepository extends JpaRepository<ModuleEnrolment, Long> {

    // Custom query method to find enrolments by student ID
    List<ModuleEnrolment> findByStudent(Student student);

    List<ModuleEnrolment> findByModule(Module module);
}
