package ca.cloudace.backend.service;


import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.ModuleEnrolment;
import ca.cloudace.backend.repository.ModuleEnrolmentRepository;

@Service
public class ModuleEnrolmentService {

    private final ModuleEnrolmentRepository moduleEnrolmentRepository;

    public ModuleEnrolmentService(ModuleEnrolmentRepository moduleEnrolmentRepository) {
        this.moduleEnrolmentRepository = moduleEnrolmentRepository;
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



}
