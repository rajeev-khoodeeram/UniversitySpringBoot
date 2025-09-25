export class Module {
    moduleId: number;
    moduleName: string
    moduleCode: string;
    moduleCredits: number;
    courseId: number;
    lecturerId?: number; //see this later
    moduleSemester?: number;

    constructor(moduleId: number, moduleName: string, moduleCode: string, moduleCredits: number, courseId: number, moduleSemester?: number
    ) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.moduleCredits = moduleCredits;
        this.courseId = courseId;
        this.moduleSemester = moduleSemester;
    }

}
