package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {


}
