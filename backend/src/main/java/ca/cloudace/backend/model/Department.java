package ca.cloudace.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private Long departmentId;

    @Column(name = "departmentName", nullable = false)
    private String departmentName;

    @Column(name = "departmentCode", nullable = false, unique = true)
    private String departmentCode;

    // one department belongs to one faculty
    @ManyToOne
    @JoinColumn(name = "facultyId", nullable = false)
    private Faculty faculty;

    // one lecturer can head only one department
    @OneToOne
    @JoinColumn(name = "lecturerId", unique = true)
    private Lecturer headOfDepartment;

    // Department has many lecturers (regular members)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Lecturer> lecturers = new ArrayList<>();

    // Department has many courses (regular members)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

    // Constructors, getters, and setters
    public Department() {
    }

    public Department(String departmentName, String departmentCode, Faculty faculty, Lecturer headOfDepartment) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Lecturer getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Lecturer headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
