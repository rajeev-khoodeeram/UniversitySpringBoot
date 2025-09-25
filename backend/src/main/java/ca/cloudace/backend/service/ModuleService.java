package ca.cloudace.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ca.cloudace.backend.model.Module;
import ca.cloudace.backend.repository.CourseRepository;
import ca.cloudace.backend.repository.ModuleRepository;

@Service
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public ModuleService(ModuleRepository moduleRepository, CourseRepository courseRepository) {
        this.moduleRepository = moduleRepository;
        this.courseRepository = courseRepository;
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

    public void deleteModuleById(Long id) {
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

    /**
     * Find modules by course ID
     * Called by CourseController
     * 
     * @param courseId
     * @return
     */
    public List<Module> findModulesByCourseId(Long courseId) {
        List<Module> modules = moduleRepository.findByCourse(courseRepository.findById(courseId).orElse(null));
        System.out.println("Modules found for course ID " + courseId + ": " + modules.size());
        return modules;
    }

}
