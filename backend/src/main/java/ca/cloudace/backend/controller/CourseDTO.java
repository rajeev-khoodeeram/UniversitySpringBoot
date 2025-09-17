package ca.cloudace.backend.controller;

import lombok.Data;

@Data
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private int courseDuration;
    private String courseLevel;
    private String courseAbbrev;
    private String departmentName; // this is normally departmentId from database

    // must also do for coordinatorName; I'll let you do that later

    public CourseDTO() {
    }

    public CourseDTO(Long courseId, String courseName, int courseDuration, String courseLevel, String courseAbbrev,
            String departmentName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseLevel = courseLevel;
        this.courseAbbrev = courseAbbrev;
        this.departmentName = departmentName;
    }

    // all getters and setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCourseAbbrev() {
        return courseAbbrev;
    }

    public void setCourseAbbrev(String courseAbbrev) {
        this.courseAbbrev = courseAbbrev;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
