package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Coordinator;
import ca.cloudace.backend.repository.CoordinatorRepository;

@Service
public class CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;

    public CoordinatorService(CoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }

    // Add service methods here as needed
    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Coordinator getCoordinatorById(Long id) {
        return coordinatorRepository.findById(id).orElse(null);
    }

    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Coordinator updateCoordinator(Long id, Coordinator coordinator) {
        if (coordinatorRepository.existsById(id)) {
            coordinator.setCoordinatorId(id);
            return coordinatorRepository.save(coordinator);
        }
        return null;
    }

    public boolean deleteCoordinator(Long id) {
        if (coordinatorRepository.existsById(id)) {
            coordinatorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
