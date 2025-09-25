import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Router } from '@angular/router';
  import { apiBaseUrl } from '../../app.config';
  @Injectable({providedIn: 'root'})


  export class AuthService {
   private base = apiBaseUrl + '/auth';

   constructor(private http: HttpClient, private router: Router) {}
    saveToken(token: string) { localStorage.setItem('auth_token', token); }
    getToken() { return localStorage.getItem('auth_token'); }
    isLoggedIn() { return !!this.getToken(); }
 
    register(payload: any) { return this.http.post(`${this.base}/register`,payload); }
 
    login(credentials: { username: string; password: string }) {
        console.log('AuthService login called with', credentials);
      return this.http.post(`${this.base}/login`, credentials);
    }

   logout() {
     const token = this.getToken();
     if (token) {
       return this.http.post(`${this.base}/logout`, {}, { headers: {
           Authorization: `Bearer ${token}` } }).subscribe({
         next: () => { localStorage.removeItem('auth_token');
           this.router.navigate(['/login']); },
         error: () => { localStorage.removeItem('auth_token');
  this.router.navigate(['/login']); }
}); }
     localStorage.removeItem('auth_token');
     this.router.navigate(['/login']);
     return ;
   }
}