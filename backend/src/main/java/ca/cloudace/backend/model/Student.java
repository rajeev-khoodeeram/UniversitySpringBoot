package ca.cloudace.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_firstname")
    private String studentFirstName;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_lastname")
    private String studentLastName;

    @Column(name = "student_phone")
    private String studentPhoneNumber;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "student_gender")
    private String studentGender;

    @Column(name = "student_dob")
    private String studentDateOfBirth;

    @Column(name = "enrolment_date")
    private String studentEnrollmentDate;

    @Column(name = "student_status")
    private String studentStatus;


    public Student() {
        // Default constructor
    }


    /**
     * Constructor for Student
     * @param studentId
     * @param studentFirstName
     * @param studentEmail
     * @param studentLastName
     * @param studentPhoneNumber
     * @param studentAddress
     * @param studentGender
     * @param studentDateOfBirth
     * @param studentEnrollmentDate
     * @param studentStatus
     */
    public Student(
        int studentId,
        String studentFirstName,
        String studentEmail,
        String studentLastName,
        String studentPhoneNumber,
        String studentAddress,
        String studentGender,
        String studentDateOfBirth,
        String studentEnrollmentDate,
        String studentStatus
    ) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentEmail = studentEmail;
        this.studentLastName = studentLastName;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentAddress = studentAddress;
        this.studentGender = studentGender;
        this.studentDateOfBirth = studentDateOfBirth;
        this.studentEnrollmentDate = studentEnrollmentDate;
        this.studentStatus = studentStatus;
    }

    /**
     * Get the student ID
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Get the student first name
     * @return studentFirstName
     */
    public String getStudentFirstName() {
        return studentFirstName;
    }

    /**
     * Get the student email
     * @return studentEmail
     */         
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Get the student last name
     * @return studentLastName
     */
    public String getStudentLastName() {
        return studentLastName;
    }

    /**
     * Get the student phone number
     * @return studentPhoneNumber
     */
    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    /**
     * Get the student address
     * @return studentAddress
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     * Get the student gender
     * @return studentGender
     */
    public String getStudentGender() {
        return studentGender;
    }

    /**
     * Get the student date of birth
     * @return studentDateOfBirth
     */
    public String getStudentDateOfBirth() {
        return studentDateOfBirth;
    }

    public String getStudentEnrollmentDate() {
        return studentEnrollmentDate;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    /**
     * Set the student ID; BUT this should only be set once upon creation
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public void setStudentDateOfBirth(String studentDateOfBirth) {
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public void setStudentEnrollmentDate(String studentEnrollmentDate) {
        this.studentEnrollmentDate = studentEnrollmentDate;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

}
