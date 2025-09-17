package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.model.Department;
import ca.cloudace.backend.model.Lecturer;
import ca.cloudace.backend.repository.DepartmentRepository;
import ca.cloudace.backend.repository.LecturerRepository;
import ca.cloudace.backend.service.LecturerService;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerController {

    private final LecturerService lecturerService;
    // Controller methods will go here
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    // without DTO
    // @GetMapping
    // public List<Lecturer> getAllLecturers() {
    // return lecturerService.getAllLecturers();
    // }

    @GetMapping
    public List<LecturerDTO> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

    // without DTO
    @GetMapping("{id}")
    public LecturerDTO getLecturerById(@PathVariable int id) {
        return lecturerService.getLecturerById(id);
    }

    // with DTO
    // @GetMapping("{id}")
    // public LecturerDTO getLecturerById(@PathVariable int id) {
    // return lecturerService.getLecturerById(id);
    // }

    @DeleteMapping("{id}")
    public void deleteLecturer(@PathVariable int id) {
        lecturerService.deleteLecturer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Lecturer createLecturer(@RequestBody LecturerDTO lecturer) {
        System.out.println("Received Lecturer: " + lecturer);
        Lecturer newLecturer = new Lecturer();
        newLecturer.setLecturerFirstName(lecturer.getLecturerFirstName());
        newLecturer.setLecturerLastName(lecturer.getLecturerLastName());
        newLecturer.setLecturerEmail(lecturer.getLecturerEmail());
        String dateOnly = lecturer.getLecturerHireDate().split("T")[0];
        newLecturer.setLecturerHireDate(java.sql.Date.valueOf(dateOnly));
        // newLecturer.setLecturerHireDate(java.sql.Date.valueOf(lecturer.getLecturerHireDate()));
        newLecturer.setLecturerTitle(lecturer.getLecturerTitle());
        // Checking the departmentId
        System.out.println("Finding Department with ID: " + lecturer.getLecturerDepartment().getDepartmentId());

        Department department = departmentRepository.findById(lecturer.getLecturerDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Department not found with id: " + lecturer.getLecturerDepartment().getLecturerId()));
        newLecturer.setDepartment(department);
        System.out.println("Converted Lecturer: " + newLecturer);
        return lecturerService.saveLecturer(newLecturer);
    }

    // without DTO
    // @PutMapping("{id}")
    // public Lecturer updateLecturer(@PathVariable int id, @RequestBody Lecturer
    // lecturer) {
    // return lecturerService.updateLecturer(id, lecturer);
    // }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable int id, @RequestBody LecturerDTO dto) {
        // return lecturerService.updateLecturer(id, lecturer);

        System.out.println("Received update for ID: " + id + " with entity: " + dto);

        Lecturer lecturer = lecturerRepository.findById(dto.getLecturerId())
                .orElseThrow(() -> new RuntimeException("Lecturer not found with id: " + dto.getLecturerId()));

        Department existingDepartment = departmentRepository.findById(dto.getLecturerDepartment().getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        System.out.println("Found Department: " + existingDepartment.getDepartmentId() + " - "
                + existingDepartment.getDepartmentName());

        lecturer.setLecturerFirstName(dto.getLecturerFirstName());
        lecturer.setLecturerLastName(dto.getLecturerLastName());
        lecturer.setLecturerEmail(dto.getLecturerEmail());
        System.out.println("Parsed Hire Date: " + dto.getLecturerHireDate());
        // System.out.println("Parsed Hire Date: " +
        // java.sql.Date.valueOf(dto.getLecturerHireDate()));
        String dateOnly = dto.getLecturerHireDate().split(" ")[0];
        System.out.println("Date Only: " + dateOnly);
        lecturer.setLecturerHireDate(java.sql.Date.valueOf(dateOnly));
        System.out.println("Date Only: " + dateOnly);
        lecturer.setLecturerTitle(dto.getLecturerTitle());
        lecturer.setDepartment(existingDepartment);

        Lecturer updatedLecturer = lecturerService.updateLecturer(id, lecturer);
        return ResponseEntity.ok(updatedLecturer);
    }

}
