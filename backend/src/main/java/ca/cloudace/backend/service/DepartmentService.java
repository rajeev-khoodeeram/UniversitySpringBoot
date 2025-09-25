package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Department;
import ca.cloudace.backend.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Add business logic methods here as needed
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department getDepartmentByName(String name) {
        System.out.println("Fetching department by name: " + name);
        return departmentRepository.findByDepartmentName(name);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setDepartmentCode(updatedDepartment.getDepartmentCode());
                    department.setDepartmentName(updatedDepartment.getDepartmentName());
                    department.setFaculty(updatedDepartment.getFaculty());
                    department.setHeadOfDepartment(updatedDepartment.getHeadOfDepartment());
                    // Update other fields as necessary
                    return departmentRepository.save(department);
                })
                .orElse(null);
    }

    /**
     * Custom method to fetch departments by faculty ID
     * Get departments by faculty ID
     * 
     * @param facultyId
     * @return
     */
    public List<Department> getDepartmentsByFacultyId(Long facultyId) {
        return departmentRepository.findByFacultyFacultyId(facultyId);
    }

}
