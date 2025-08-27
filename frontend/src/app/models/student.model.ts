export class Student {
    studentId: number;
    studentFirstName: string;
    studentEmail:          string;
    studentLastName:       string;
    studentPhoneNumber:    string;
    studentAddress:        string;
    studentGender:         string;
    studentDateOfBirth:    Date;
    studentEnrollmentDate: Date;
    studentStatus:         string;

    constructor(
        studentId: number,
        firstName: string,
        email: string,
        lastName: string,
        phoneNumber: string,
        address: string,
        gender: string,
        dateOfBirth: Date,
        enrollmentDate: Date,
        status: string
    ) {
        this.studentId = studentId;
        this.studentFirstName = firstName;
        this.studentEmail = email;
        this.studentLastName = lastName;
        this.studentPhoneNumber = phoneNumber;
        this.studentAddress = address;
        this.studentGender = gender;
        this.studentDateOfBirth = dateOfBirth;
        this.studentEnrollmentDate = enrollmentDate;
        this.studentStatus = status;
    }

}
