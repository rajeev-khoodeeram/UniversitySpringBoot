package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.ModuleEnrolment;


@Repository
public interface ModuleEnrolmentRepository extends JpaRepository<ModuleEnrolment, Long> {

}
