package ca.cloudace.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.cloudace.backend.model.Module;
import ca.cloudace.backend.service.ModuleService;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    // Define endpoints for module operations here

    /**
     * Get all modules
     * Without DTO
     * 
     * @return
     */
    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    /**
     * Get module by id
     * 
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Module getModuleById(@RequestParam Long id) {
        return moduleService.getModuleById(id);
    }

    /**
     * Delete module by id
     * 
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteModuleById(@RequestParam Long id) {
        moduleService.deleteModule(id);
        // Handle response or return status
    }

    /**
     * Create a new module
     * 
     * @param module
     * @return
     */
    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.saveModule(module);
    }

    /**
     * Update an existing module
     * 
     * @param id
     * @param module
     * @return
     */
    @PutMapping("{id}")
    public Module updateModule(@PathVariable Long id, @RequestBody Module module) {
        return moduleService.updateModule(id, module);
    }
}