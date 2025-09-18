package ca.cloudace.backend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "moduleenrolment")
public class ModuleEnrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrolmentId")
    private Long enrolmentId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "moduleId", nullable = false)
    private Module module;

    @Column(name = "enrolmentDate", nullable = false)
    private Date enrolmentDate;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "status", nullable = false)
    private String status;

    // Constructors, getters, and setters

    public ModuleEnrolment() {
    }

    public ModuleEnrolment(Student student, Module module, Date enrolmentDate, String grade, String status) {
        this.student = student;
        this.module = module;
        this.enrolmentDate = enrolmentDate;
        this.grade = grade;
        this.status = status;
    }

    public Long getEnrolmentId() {
        return enrolmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Date getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(Date enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEnrolmentId(Long enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

}
