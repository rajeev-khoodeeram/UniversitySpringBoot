package ca.cloudace.backend.controller;

import lombok.Data;

@Data
public class CoordinatorDTO {

    private Integer coordinatorId;
    private String coordinatorName;
    private String coordinatorEmail;
    private String coordinatorPhone;
    private int courseId; // but we will to display program name

    public CoordinatorDTO() {
    }

    public CoordinatorDTO(Integer coordinatorId, String coordinatorName, String coordinatorEmail,
            String coordinatorPhone, int courseId) {
        this.coordinatorId = coordinatorId;
        this.coordinatorName = coordinatorName;
        this.coordinatorEmail = coordinatorEmail;
        this.coordinatorPhone = coordinatorPhone;
        this.courseId = courseId;
    }

    public Integer getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Integer coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getCoordinatorEmail() {
        return coordinatorEmail;
    }

    public void setCoordinatorEmail(String coordinatorEmail) {
        this.coordinatorEmail = coordinatorEmail;
    }

    public String getCoordinatorPhone() {
        return coordinatorPhone;
    }

    public void setCoordinatorPhone(String coordinatorPhone) {
        this.coordinatorPhone = coordinatorPhone;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setProgramId(int courseId) {
        this.courseId = courseId;
    }

}
