package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.model.ModuleEnrolment;
import ca.cloudace.backend.service.ModuleEnrolmentService;

@RestController
@RequestMapping("/api/module-enrolments")
public class ModuleEnrolmentController {

    private final ModuleEnrolmentService moduleEnrolmentService;

    public ModuleEnrolmentController(ModuleEnrolmentService moduleEnrolmentService) {
        this.moduleEnrolmentService = moduleEnrolmentService;
    }

    // Define endpoints for CRUD operations here
    @GetMapping
    public List<ModuleEnrolment> getAllModuleEnrolments() {

        return moduleEnrolmentService.getAllModuleEnrolments();
    }

    @GetMapping("/{id}")
    public ModuleEnrolment getModuleEnrolmentById(@PathVariable Long id) {
        return moduleEnrolmentService.getModuleEnrolmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteModuleEnrolment(@PathVariable Long id) {
        moduleEnrolmentService.deleteModuleEnrolment(id);
    }

    @PutMapping("/{id}")
    public ModuleEnrolment updateModuleEnrolment(@PathVariable Long id, @RequestBody ModuleEnrolment moduleEnrolment) {
        return moduleEnrolmentService.updateModuleEnrolment(id, moduleEnrolment);
    }

    @PostMapping
    public ModuleEnrolment createModuleEnrolment(@RequestBody ModuleEnrolment moduleEnrolment) {
        return moduleEnrolmentService.saveModuleEnrolment(moduleEnrolment);
    }

    /**
     * Get all enrolments to which a student has enrolled (by studentId)
     * 
     * @param id
     * @return
     */
    @GetMapping("/student/{id}")
    public List<ModuleEnrolment> getModuleEnrolmentsByStudentId(@PathVariable int id) {
        return moduleEnrolmentService.getModuleEnrolmentsByStudentId(id);
    }

    /**
     * Get all enrolments for a specific module (by moduleId)
     * 
     * @param id
     * @return
     */
    @GetMapping("/module/{id}")
    public List<ModuleEnrolment> getModuleEnrolmentsByModuleId(@PathVariable Long id) {
        return moduleEnrolmentService.getModuleEnrolmentsByModuleId(id);
    }

}
