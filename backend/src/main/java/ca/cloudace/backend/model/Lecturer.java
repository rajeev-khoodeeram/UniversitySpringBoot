package ca.cloudace.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturerId")
    private int lecturerId;

    @Column(name = "lecturerFirstName", nullable = false)
    private String lecturerFirstName;

    @Column(name = "lecturerLastName", nullable = false)
    private String lecturerLastName;

    @Column(name = "lecturerEmail", nullable = false)
    private String lecturerEmail;

    @Column(name = "lecturerHireDate", nullable = false)
    private Date lecturerHireDate;

    @Column(name = "lecturerTitle", nullable = false)
    private String lecturerTitle;

    // many lecturers belong to one department
    @ManyToOne
    @JoinColumn(name = "departmentId", nullable = false)
    @JsonBackReference
    private Department department;

    // headship : one lecturer can head only one department
    @OneToOne(mappedBy = "headOfDepartment")
    @JsonBackReference
    private Department headedDepartment;

    @OneToOne(mappedBy = "facultyDean")
    @JsonBackReference
    private Faculty facultyDean;

    // Constructors, getters, and setters
    public Lecturer() {
    }

    public Lecturer(String lecturerFirstName, String lecturerLastName, String lecturerEmail, Date lecturerHireDate,
            String lecturerTitle, Department department) {
        this.lecturerFirstName = lecturerFirstName;
        this.lecturerLastName = lecturerLastName;
        this.lecturerEmail = lecturerEmail;
        this.lecturerHireDate = lecturerHireDate;
        this.lecturerTitle = lecturerTitle;
        this.department = department;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerFirstName() {
        return lecturerFirstName;
    }

    public void setLecturerFirstName(String lecturerFirstName) {
        this.lecturerFirstName = lecturerFirstName;
    }

    public String getLecturerLastName() {
        return lecturerLastName;
    }

    public void setLecturerLastName(String lecturerLastName) {
        this.lecturerLastName = lecturerLastName;
    }

    public Date getLecturerHireDate() {
        return lecturerHireDate;
    }

    public void setLecturerHireDate(Date lecturerHireDate) {
        this.lecturerHireDate = lecturerHireDate;
    }

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail;
    }

    public String getLecturerTitle() {
        return lecturerTitle;
    }

    public void setLecturerTitle(String lecturerTitle) {
        this.lecturerTitle = lecturerTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getHeadedDepartment() {
        return headedDepartment;
    }

    @Override
    public String toString() {
        return "Lecturer [lecturerId=" + lecturerId + ", lecturerFirstName="

                + lecturerFirstName + ", lecturerLastName=" + lecturerLastName + ", lecturerEmail=" + lecturerEmail
                + ", lecturerHireDate=" + lecturerHireDate + ", lecturerTitle=" + lecturerTitle + ", department="
                + department + "]";
    }

}
