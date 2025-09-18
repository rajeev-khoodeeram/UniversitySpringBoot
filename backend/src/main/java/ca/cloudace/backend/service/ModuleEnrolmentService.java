package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.ModuleEnrolment;
import ca.cloudace.backend.repository.ModuleEnrolmentRepository;
import ca.cloudace.backend.repository.ModuleRepository;
import ca.cloudace.backend.repository.StudentRepository;

@Service
public class ModuleEnrolmentService {

    private final StudentRepository studentRepository;

    private final ModuleEnrolmentRepository moduleEnrolmentRepository;

    @Autowired
    private final ModuleRepository moduleRepository;

    public ModuleEnrolmentService(ModuleEnrolmentRepository moduleEnrolmentRepository,
            StudentRepository studentRepository, ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
        this.moduleEnrolmentRepository = moduleEnrolmentRepository;
        this.studentRepository = studentRepository;
    }

    // Add service methods as needed
    public List<ModuleEnrolment> getAllModuleEnrolments() {
        return moduleEnrolmentRepository.findAll();
    }

    public ModuleEnrolment getModuleEnrolmentById(Long id) {
        return moduleEnrolmentRepository.findById(id).orElse(null);
    }

    public ModuleEnrolment saveModuleEnrolment(ModuleEnrolment moduleEnrolment) {
        return moduleEnrolmentRepository.save(moduleEnrolment);
    }

    public void deleteModuleEnrolment(Long id) {
        moduleEnrolmentRepository.deleteById(id);
    }

    public ModuleEnrolment updateModuleEnrolment(Long id, ModuleEnrolment updatedEnrolment) {
        return moduleEnrolmentRepository.findById(id)
                .map(enrolment -> {
                    enrolment.setStudent(updatedEnrolment.getStudent());
                    enrolment.setModule(updatedEnrolment.getModule());
                    enrolment.setEnrolmentDate(updatedEnrolment.getEnrolmentDate());
                    enrolment.setGrade(updatedEnrolment.getGrade());
                    enrolment.setStatus(updatedEnrolment.getStatus());
                    return moduleEnrolmentRepository.save(enrolment);
                })
                .orElse(null);
    }

    public List<ModuleEnrolment> getModuleEnrolmentsByStudentId(int studentId) {
        return moduleEnrolmentRepository.findByStudent(studentRepository.findById(studentId).orElse(null));
    }

    public List<ModuleEnrolment> getModuleEnrolmentsByModuleId(Long moduleId) {
        return moduleEnrolmentRepository.findByModule(moduleRepository.findById(moduleId).orElse(null));
    }

}
