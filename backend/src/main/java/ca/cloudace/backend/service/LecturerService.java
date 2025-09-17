package ca.cloudace.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.controller.DepartmentDTO;
import ca.cloudace.backend.controller.LecturerDTO;
import ca.cloudace.backend.model.Department;
import ca.cloudace.backend.model.Lecturer;
import ca.cloudace.backend.repository.LecturerRepository;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    // Add service methods here as needed
    public List<LecturerDTO> getAllLecturers() {
        // without DTO
        // return lecturerRepository.findAll();

        List<LecturerDTO> list = lecturerRepository.findAll().stream()
                .map(l -> {
                    Department dept = l.getDepartment();
                    DepartmentDTO deptDTO = new DepartmentDTO();
                    deptDTO.setDepartmentCode(dept.getDepartmentCode());
                    deptDTO.setDepartmentName(dept.getDepartmentName());
                    deptDTO.setFacultyId(dept.getFaculty().getFacultyId());

                    return new LecturerDTO(deptDTO, l.getLecturerEmail(),
                            l.getLecturerFirstName(),
                            l.getLecturerHireDate().toString(), l.getLecturerId(),
                            l.getLecturerLastName(),
                            l.getLecturerTitle());
                }).collect(Collectors.toList());

        return list;
    }

    /**
     * Get lecturer by ID without DTO
     * 
     * @param id
     * @return
     */
    // public Lecturer getLecturerOnlyById(int id) {
    // return lecturerRepository.findById(id).orElse(null);
    // }

    /**
     * Get lecturer by ID with DTO
     * 
     * @param id
     * @return
     */
    public LecturerDTO getLecturerById(int id) {
        // return lecturerRepository.findById(id).orElse(null);

        LecturerDTO lecturer = lecturerRepository.findById(id)
                .map(l -> {
                    Department dept = l.getDepartment();
                    DepartmentDTO deptDTO = new DepartmentDTO();
                    deptDTO.setDepartmentCode(dept.getDepartmentCode());
                    deptDTO.setDepartmentName(dept.getDepartmentName());
                    deptDTO.setFacultyId(dept.getFaculty().getFacultyId());

                    return new LecturerDTO(deptDTO, l.getLecturerEmail(), l.getLecturerFirstName(),
                            l.getLecturerHireDate().toString(), l.getLecturerId(), l.getLecturerLastName(),
                            l.getLecturerTitle());
                }).orElse(null);

        return lecturer;

    }

    /**
     * Save a lecturer (here no DTO because the controller handles the DTO for us)
     * 
     * @param lecturer
     * @return
     */
    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(int id) {
        lecturerRepository.deleteById(id);
    }

    public Lecturer updateLecturer(int id, Lecturer updatedLecturer) {
        return lecturerRepository.findById(id).map(lecturer -> {
            lecturer.setLecturerFirstName(updatedLecturer.getLecturerFirstName());
            lecturer.setLecturerLastName(updatedLecturer.getLecturerLastName());
            lecturer.setLecturerEmail(updatedLecturer.getLecturerEmail());
            lecturer.setLecturerHireDate(updatedLecturer.getLecturerHireDate());
            lecturer.setLecturerTitle(updatedLecturer.getLecturerTitle());
            lecturer.setDepartment(updatedLecturer.getDepartment());
            return lecturerRepository.save(lecturer);
        }).orElse(null);
    }

}
