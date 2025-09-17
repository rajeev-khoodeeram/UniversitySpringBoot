package ca.cloudace.backend.model;

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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private Long courseId;

    @Column(name = "courseName", nullable = false)
    private String courseName;

    @Column(name = "courseDuration", nullable = false)
    private int courseDuration; // in years

    @Column(name = "courseLevel", nullable = false)
    private String courseLevel;

    @Column(name = "courseAbbrev", nullable = false, unique = true)
    private String courseAbbrev;

    @ManyToOne
    @JoinColumn(name = "departmentId", nullable = false)
    @JsonBackReference
    private Department department;

    // we are missing coordinator here; for all courses, there is one coordinator
    // its going to be a one-to-one relationship
    // one course has one coordinator and one coordinator has one course
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "courseId", nullable = false, unique = true)
    private Coordinator coordinator;

    // Constructors, getters, and setters

    public Course() {
    }

    public Course(String courseName, int courseDuration, String courseLevel, String courseAbbrev,
            Department department, Coordinator coordinator) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseLevel = courseLevel;
        this.courseAbbrev = courseAbbrev;
        this.department = department;
        this.coordinator = coordinator;
    }

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDuration=" + courseDuration
                + ", courseLevel=" + courseLevel + ", courseAbbrev=" + courseAbbrev + ", department="
                + (department != null ? department.getDepartmentName() : "null") + "]";
    }

}
