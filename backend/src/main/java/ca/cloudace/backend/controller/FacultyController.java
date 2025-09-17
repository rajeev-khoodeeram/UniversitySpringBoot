package ca.cloudace.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.service.FacultyService;


@RestController
@RequestMapping("/api/faculty")
@CrossOrigin(origins = "http://localhost:4200") // Adjust the origin as needed
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Define your endpoints here, e.g., CRUD operations for Faculty
    @GetMapping
    public ResponseEntity<?> getAllFaculties() {
        var faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Long id) {
        var faculty = facultyService.getFacultyById(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        boolean deleted = facultyService.deleteFaculty(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable Long id, @RequestBody ca.cloudace.backend.model.Faculty updatedFaculty) {
        var faculty = facultyService.updateFaculty(id, updatedFaculty);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody ca.cloudace.backend.model.Faculty newFaculty) {
        var faculty = facultyService.saveFaculty(newFaculty);
        return ResponseEntity.status(201).body(faculty);
    }
    


    // Add other CRUD endpoints as needed
    @GetMapping("/count")
    public ResponseEntity<Long> countFaculties() {
        long count = facultyService.countFaculties();
        return ResponseEntity.ok(count);
    }


}
