package ca.cloudace.backend.controller;

import lombok.Data;

@Data
public class ModuleDTO {
    private Integer moduleId;
    private String moduleName;
    private String moduleCode;
    private Integer moduleCredits;
    private int moduleSemester;
    private int lecturerId; // but we will to display lecturer name
    private int courseId; // but we will to display course name

    // we first do a test without DTO !!

    public ModuleDTO() {
    }

    public ModuleDTO(Integer moduleId, String moduleName, String moduleCode,
            Integer moduleCredits, int moduleSemester, int lecturerId, int courseId) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
        this.lecturerId = lecturerId;
        this.courseId = courseId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getModuleSemester() {
        return moduleSemester;
    }

    public void setModuleSemester(int moduleSemester) {
        this.moduleSemester = moduleSemester;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Integer getModuleCredits() {
        return moduleCredits;
    }

    public void setModuleCredits(Integer moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

}
