package ca.cloudace.backend.model;

import jakarta.persistence.Column;  
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @Column (name = "facultyid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @Column(name = "facultyname", nullable = false)
    private String facultyName;

    @Column(name = "facultycode", nullable = false, unique = true)
    private String facultyCode;

    // Constructors, getters, and setters
    
    public Faculty() {
    }

    /**
     * Constructor for Faculty (Id is auto-generated !!)
     * @param facultyName
     * @param facultyCode
     */
    public Faculty(String facultyName, String facultyCode) {
        this.facultyName = facultyName;
        this.facultyCode = facultyCode;
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

}
