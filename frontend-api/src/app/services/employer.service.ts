import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployerService {
  API_BASIC_URL = 'http://localhost:8082/employer/';

  constructor(private http: HttpClient) {
  }

  getEmployerById(id: number): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + id);
  }

  getAllEmployers(): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL);
  }

  updateEmployer(body: any): Observable<any> {
    return this.http.put(this.API_BASIC_URL, body);
  }

  deleteEmployer(id: number): Observable<any> {
    return this.http.delete(this.API_BASIC_URL + id);
  }

  saveEmployer(body: any): Observable<any> {
    return this.http.post(this.API_BASIC_URL, body);
  }
}
