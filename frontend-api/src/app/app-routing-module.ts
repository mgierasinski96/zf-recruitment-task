import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListEmployeesEmployersComponent} from './views/list-employees-employers/list-employees-employers.component';
import {CreateEmployeeEmployerComponent} from './views/create-employee-employer/create-employee-employer.component';
import {EmployeeViewComponent} from './views/employee-view/employee-view.component';

const routes: Routes = [
  {path: 'listEmployeesEmployers', component: ListEmployeesEmployersComponent},
  {path: 'addForm', component: CreateEmployeeEmployerComponent},
  {path: 'employerView/:id', component: EmployeeViewComponent, pathMatch: 'full'},
  {path: 'employeeView/:id', component: EmployeeViewComponent, pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
