package ca.cloudace.backend.controller;

import lombok.Data;

@Data
public class LecturerDTO {
    private Integer lecturerId;
    private String lecturerFirstName;
    private String lecturerLastName;
    private String lecturerEmail;
    private String lecturerHireDate;
    private String lecturerTitle;
    private DepartmentDTO lecturerDepartment;

    public LecturerDTO() {
    }

    public LecturerDTO(DepartmentDTO lecturerDepartment, String lecturerEmail, String lecturerFirstName,
            String lecturerHireDate, Integer lecturerId, String lecturerLastName, String lecturerTitle) {
        this.lecturerDepartment = lecturerDepartment;
        this.lecturerEmail = lecturerEmail;
        this.lecturerFirstName = lecturerFirstName;
        this.lecturerHireDate = lecturerHireDate;
        this.lecturerId = lecturerId;
        this.lecturerLastName = lecturerLastName;
        this.lecturerTitle = lecturerTitle;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
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

    public String getLecturerEmail() {
        return lecturerEmail;
    }

    public void setLecturerEmail(String lecturerEmail) {
        this.lecturerEmail = lecturerEmail;
    }

    public String getLecturerHireDate() {
        return lecturerHireDate;
    }

    public void setLecturerHireDate(String lecturerHireDate) {
        this.lecturerHireDate = lecturerHireDate;
    }

    public String getLecturerTitle() {
        return lecturerTitle;
    }

    public void setLecturerTitle(String lecturerTitle) {
        this.lecturerTitle = lecturerTitle;
    }

    public DepartmentDTO getLecturerDepartment() {
        return lecturerDepartment;
    }

    public void setLecturerDepartment(DepartmentDTO lecturerDepartment) {
        this.lecturerDepartment = lecturerDepartment;
    }

}
