package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Module;
import ca.cloudace.backend.repository.ModuleRepository;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    // Add service methods here
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    public Module saveModule(Module module) {
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public Module updateModule(Long id, Module updatedModule) {
        return moduleRepository.findById(id).map(module -> {
            module.setModuleCode(updatedModule.getModuleCode());
            module.setModuleName(updatedModule.getModuleName());
            module.setModuleCredits(updatedModule.getModuleCredits());
            module.setModuleSemester(updatedModule.getModuleSemester());
            module.setCourse(updatedModule.getCourse());
            module.setLecturer(updatedModule.getLecturer());
            

            // Update other fields as necessary
            return moduleRepository.save(module);
        }).orElse(null);
    }



}
