package ca.cloudace.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleId")
    private Long moduleId;

    @Column(name = "moduleName", nullable = false)
    private String moduleName;
    
    @Column(name = "moduleCode", nullable = false, unique = true)
    private String moduleCode;

    @Column(name = "moduleCredits", nullable = false)
    private int moduleCredits; // in credits

    @Column(name = "moduleSemester", nullable = false)
    private int moduleSemester; // in semester

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "lecturerId", nullable = false)
    private Lecturer lecturer;

    // Constructors, getters, and setters

    public Module() {
    }

    public Module(String moduleName, String moduleCode, int moduleCredits, Course course) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.moduleCredits = moduleCredits;
        this.course = course;
    }   

    public Long getModuleId() {
        return moduleId;
    }
    public void setModuleId(Long moduleId) {
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
    public int getModuleCredits() {
        return moduleCredits;
    }
    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getModuleSemester() {
        return moduleSemester;
    }

    public void setModuleSemester(int moduleSemester) {
        this.moduleSemester = moduleSemester;
    }

}
