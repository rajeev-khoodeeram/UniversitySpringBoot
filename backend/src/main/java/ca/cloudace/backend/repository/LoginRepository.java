package ca.cloudace.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.cloudace.backend.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByUsername(String username);

    Login findByUserId(int userId);

    Login findByUserType(String userType);

    boolean existsByUsername(String username);

    boolean existsByUserId(int userId);

}
