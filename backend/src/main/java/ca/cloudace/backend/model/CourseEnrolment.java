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
@Table(name = "courseenrolment")
public class CourseEnrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseEnrolId")
    private Long courseEnrolId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;

    @Column(name = "courseEnrolDate", nullable = false)
    private Date courseEnrolDate;

    @Column(name = "courseEnrolStatus", nullable = false)
    private String courseEnrolStatus;

    @Column(name = "courseEnrolGraduation", nullable = true)
    private String courseEnrolGraduation;


    // Constructors, getters, and setters

    public CourseEnrolment() {
    }

    public CourseEnrolment(Student student, Course course, Date courseEnrolDate, String courseEnrolStatus, String courseEnrolGraduation) {
        this.student = student;
        this.course = course;
        this.courseEnrolDate = courseEnrolDate;
        this.courseEnrolStatus = courseEnrolStatus;
        this.courseEnrolGraduation = courseEnrolGraduation;
    }

    public Long getCourseEnrolId() {
        return courseEnrolId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

   
    public Date getCourseEnrolDate() {
        return courseEnrolDate;
    }

    public void setCourseEnrolDate(Date courseEnrolDate) {
        this.courseEnrolDate = courseEnrolDate;
    }

    public String getCourseEnrolStatus() {
        return courseEnrolStatus;
    }

    public void setCourseEnrolStatus(String courseEnrolStatus) {
        this.courseEnrolStatus = courseEnrolStatus;
    }

    public String getCourseEnrolGraduation() {
        return courseEnrolGraduation;
    }

    public void setCourseEnrolGraduation(String courseEnrolGraduation) {
        this.courseEnrolGraduation = courseEnrolGraduation;
    }
       


}
