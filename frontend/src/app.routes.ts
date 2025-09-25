import { Routes } from '@angular/router';
import { AppLayout } from './app/layout/component/app.layout';
import { Dashboard } from './app/pages/dashboard/dashboard';
import { Documentation } from './app/pages/documentation/documentation';
import { Landing } from './app/pages/landing/landing';
import { Notfound } from './app/pages/notfound/notfound';
import { FacultyDetailsComponent } from './app/components/faculty-details-component/faculty-details-component';
import { CourseListComponent } from './app/components/course-list-component/course-list-component';
import { AuthGuard } from '@/guards/auth.guard';
export const appRoutes: Routes = [
    {
        path: '',
        component: Landing  , // let us show the landing page 
        children: [
            //{ path: '', component: Dashboard },
            //{ path: 'uikit', loadChildren: () => import('./app/pages/uikit/uikit.routes') },
            //{ path: 'documentation', component: Documentation },
           // { path: 'pages', loadChildren: () => import('./app/pages/pages.routes') }
        ]
    },
    { path: 'landing', component: Landing },
    { path: 'faculty/:id', component: FacultyDetailsComponent },
    { path: 'courses/dept/:id', component: CourseListComponent },
    {path : 'dash', component: AppLayout, canActivate: [AuthGuard],
       children: [
            { path: '', component: Dashboard ,}] 
    },
    {path: 'uikit', component : AppLayout, children :
        [{path:'',loadChildren: () => import('./app/pages/uikit/uikit.routes') }]   ,
        canActivate: [AuthGuard]
    },
    { path: 'documentation', component : AppLayout, 
        children: [{ path: '', component: Documentation }] ,
        canActivate: [AuthGuard]
    },
    { path: 'pages', component : AppLayout, children :
        [{path:'',loadChildren: () => import('./app/pages/pages.routes')}]   ,
        canActivate: [AuthGuard]
    },
    { path: 'notfound', component: Notfound },
    { path: 'auth', loadChildren: () => import('./app/pages/auth/auth.routes') },
    { path: '**', redirectTo: '/notfound' }
];
