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
@Table(name = "coordinator")
public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinatorId")
    private Long coordinatorId;

    @Column(name = "coordinatorName", nullable = false)
    private String coordinatorName;

    @Column(name = "coordinatorEmail", nullable = false, unique = true)
    private String coordinatorEmail;

    // added while doing CourseController
    @OneToOne
    @JoinColumn(name = "courseId", nullable = false, unique = true)
    private Course course;

    // Constructors, getters, and setters

    public Coordinator() {
    }

    public Coordinator(String coordinatorName, String coordinatorEmail, Course course) {
        this.coordinatorName = coordinatorName;
        this.coordinatorEmail = coordinatorEmail;
        this.course = course;

    }

    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
