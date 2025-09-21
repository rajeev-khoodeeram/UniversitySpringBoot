import { Lecturer } from './lecturer';

export class Faculty {
    facultyId!: number;
    facultyName!: string
    facultyCode!: string;
    facultyPhone!: string;
    facultyDean!: Lecturer;
}
