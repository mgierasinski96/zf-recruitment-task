import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  API_BASIC_URL = 'http://localhost:8082/employee/';

  constructor(private http: HttpClient) {
  }

  getEmployeeById(id: number): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + id);
  }

  setBossToNull(id: number): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + 'setBossToNull/' + id);
  }

  getAllEmployees(): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL);
  }

  updateEmployee(body: any): Observable<any> {
    return this.http.put(this.API_BASIC_URL, body);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(this.API_BASIC_URL + id);
  }

  saveEmployee(body: any): Observable<any> {
    return this.http.post(this.API_BASIC_URL, body);
  }

  getEmployeesForBoss(bossId: number): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + 'forBoss/' + bossId);
  }

  getEmployeesWithoutBoss(): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + 'allWithoutBoss');
  }

  getAndSetEmployeeToNewBoss(bossId: number, employeeId: number): Observable<any> {
    return this.http.get<any>(this.API_BASIC_URL + 'setBoss/' + bossId + '/' + employeeId);
  }

}
