package ca.cloudace.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.cloudace.backend.model.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

}
