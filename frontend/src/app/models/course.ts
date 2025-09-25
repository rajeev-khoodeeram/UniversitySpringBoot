export class Course {
    courseId: number;
    courseName: string;
    courseAbbrev: string
    courseDuration: number;
    courseLevel: string;
    departmentId: number;
    modules?: any[]; // Optional array to hold modules associated with the course

    constructor(courseId: number, courseName: string, courseAbbrev: string, courseDuration: number, courseLevel: string, departmentId: number) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseAbbrev = courseAbbrev;
        this.courseDuration = courseDuration;
        this.courseLevel = courseLevel;
        this.departmentId = departmentId;
    }
}
