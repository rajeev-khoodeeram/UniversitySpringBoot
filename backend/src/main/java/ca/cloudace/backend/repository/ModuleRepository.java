package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

}
