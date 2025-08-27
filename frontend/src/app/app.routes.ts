import { Routes } from '@angular/router';
import { App } from './app';
import { StudentListComponent } from './components/student-list-component/student-list-component';
import { StudentFormComponent } from './components/student-form-component/student-form-component';

export const routes: Routes = [
    {
        path: 'students',
        component: StudentListComponent
    },
    {
        path: "",
        component: App
    },
    { path: "students/add", component: StudentFormComponent },
    { path: "students/edit/:id", component: StudentFormComponent },
    { path: "students/view/:id", component: StudentFormComponent }

];
