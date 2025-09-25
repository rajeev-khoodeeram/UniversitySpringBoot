package ca.cloudace.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Course;
import ca.cloudace.backend.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    // Add custom query methods if needed
    List<Module> findByCourse(Course course);
}
