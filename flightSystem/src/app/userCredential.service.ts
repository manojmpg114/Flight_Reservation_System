import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserCredential } from './models/userCredential.model';

const httpOptions = {
   headers: new HttpHeaders({'Content-Type': 'application/json'})
 };
 
 @Injectable({
   providedIn: 'root'
})

export class UserCredentialService {
   constructor(private http:HttpClient) {}

   private userUrl = 'http://localhost:8080/userCredential';

   public getUserCredentials() {
      return this.http.get<UserCredential[]>(this.userUrl + "/getAll");
   }

   public getUserCredential(userCredential) {
      return this.http.get(this.userUrl + "/get/" + userCredential.id);
   }

   public deleteUserCredential(userCredential) {
      return this.http.delete(this.userUrl + "/delete/" + userCredential.id);
   }

   public createUserCredential(userCredential) {
      return this.http.post<UserCredential>(this.userUrl + "/add", userCredential);
   }

   public updateUserCredential(userCredential) {
      return this.http.put<UserCredential>(this.userUrl + "/update/" + userCredential.id, userCredential);
   }
}