package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Faculty;
import ca.cloudace.backend.repository.FacultyRepository;



@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    // Add business logic methods here as needed
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public boolean deleteFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            return false;
        }
        facultyRepository.deleteById(id);
        return true;
        
    }

    public Faculty updateFaculty(Long id, Faculty updatedFaculty) {
        return facultyRepository.findById(id).map(faculty -> {
            faculty.setFacultyName(updatedFaculty.getFacultyName());
            faculty.setFacultyCode(updatedFaculty.getFacultyCode());
            return facultyRepository.save(faculty);
        }).orElse(null);
    }
   
    public long countFaculties() {
         return facultyRepository.count();
     }


}
