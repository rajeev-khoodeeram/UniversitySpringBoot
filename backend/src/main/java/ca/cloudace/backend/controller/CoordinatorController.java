package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.model.Coordinator;
import ca.cloudace.backend.service.CoordinatorService;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    // Define endpoints for coordinator operations here
    @GetMapping("/{id}")
    public Coordinator getCoordinatorById(@PathVariable Long id) {
        System.out.println("Coordinator details for ID: " + id);
        return coordinatorService.getCoordinatorById(id);

        // Handle response or return status; this is another way to return response
        // ResponseEntity<Coordinator> coordinator =
        // coordinatorService.getCoordinatorById(id);
        // return ResponseEntity.ok(coordinator);
    }

    @GetMapping
    public List<Coordinator> getAllCoordinators() {
        System.out.print("List of all coordinators");
        return coordinatorService.getAllCoordinators();
    }

    @DeleteMapping("/{id}")
    public void deleteCoordinatorById(@RequestParam Long id) {
        coordinatorService.deleteCoordinator(id);
        // Handle response or return status
    }

    @PostMapping
    public String createCoordinator(@RequestBody String coordinator) {
        // Implement creation logic
        coordinatorService.createCoordinator(null); // Replace null with actual Coordinator object
        // you can use saveCoordinator method if you create it in service
        return "Coordinator created: " + coordinator;
    }

    @PutMapping("/{id}")
    public String updateCoordinator(@PathVariable Long id, @RequestBody String coordinator) {
        // Implement update logic
        coordinatorService.updateCoordinator(id, null); // Replace null with actual Coordinator object
        return "Coordinator updated: " + coordinator;
    }

    // Additional methods as needed
    // example get all coordinators for all programs

}
