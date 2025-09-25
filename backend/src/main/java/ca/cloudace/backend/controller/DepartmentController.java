package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import ca.cloudace.backend.model.Department;
import ca.cloudace.backend.model.Faculty;
import ca.cloudace.backend.model.Lecturer;
import ca.cloudace.backend.repository.DepartmentRepository;
import ca.cloudace.backend.repository.FacultyRepository;
import ca.cloudace.backend.repository.LecturerRepository;
import ca.cloudace.backend.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Controller methods will go here
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

    /**
     * Create a new department.
     * 
     * @param department
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO dto) {
        // print the DTO for testing purposes
        System.out.println("Received Department DTO: " + dto);

        Department dept = new Department();
        dept.setDepartmentName(dto.getDepartmentName());
        dept.setDepartmentCode(dto.getDepartmentCode());

        // now we load faculty
        Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getFacultyId()));
        dept.setFaculty(faculty);

        // now we load the lecturer
        Lecturer lecturer = lecturerRepository.findById(dto.getLecturerId())
                .orElseThrow(() -> new RuntimeException("Lecturer not found with id: " + dto.getLecturerId()));
        dept.setHeadOfDepartment(lecturer);

        Department createdDepartment = departmentService.saveDepartment(dept);
        return ResponseEntity.status(201).body(createdDepartment);
    }

    /**
     * Delete a department by its ID.
     * 
     * @param departmentId
     * @return
     */
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update an existing department.
     * 
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO dto) {
        System.out.println("Received update for ID: " + id + " with entity: " + dto);

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        existingDepartment.setDepartmentName(dto.getDepartmentName());
        existingDepartment.setDepartmentCode(dto.getDepartmentCode());

        Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getFacultyId()));

        existingDepartment.setFaculty(faculty);

        Lecturer lecturer = lecturerRepository.findById(dto.getLecturerId())
                .orElseThrow(() -> new RuntimeException("Lecturer not found with id: " + dto.getLecturerId()));
        existingDepartment.setHeadOfDepartment(lecturer);

        Department updatedDepartment = departmentService.updateDepartment(id, existingDepartment);
        return ResponseEntity.ok(updatedDepartment);
    }

    /**
     * Get list of departments by faculty ID.
     * Used in the frontend (FacultyDetailsComponent) to show the list of
     * departments
     * 
     * @param facultyId
     * @return
     */
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<Department>> getListOfDepartmentsByFacultyId(@PathVariable Long facultyId) {
        List<Department> departments = departmentService.getDepartmentsByFacultyId(facultyId);
        return ResponseEntity.ok(departments);
    }

}
