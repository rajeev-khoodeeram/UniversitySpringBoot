package ca.cloudace.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @Column(name = "facultyid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @Column(name = "facultyname", nullable = false)
    private String facultyName;

    @Column(name = "facultycode", nullable = false, unique = true)
    private String facultyCode;

    @Column(name = "facultyPhone", nullable = true)
    private String facultyPhone;

    @Column(name = "facultyEmail", nullable = true)
    private String facultyEmail;

    @OneToOne
    @JoinColumn(name = "facultyDean", unique = true)
    private Lecturer facultyDean;

    public Faculty() {
    }

    public Faculty(String facultyName, String facultyCode, String facultyPhone, String facultyEmail) {
        this.facultyName = facultyName;
        this.facultyCode = facultyCode;
        this.facultyPhone = facultyPhone;
        this.facultyEmail = facultyEmail;

    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyPhone() {
        return facultyPhone;
    }

    public void setFacultyPhone(String facultyPhone) {
        this.facultyPhone = facultyPhone;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public Lecturer getFacultyDean() {
        return facultyDean;
    }

    public void setFacultyDean(Lecturer facultyDean) {
        this.facultyDean = facultyDean;
    }

}