import {Component, Input, OnInit, ViewChild} from '@angular/core';

import {EmployeeService} from '../../services/employee.service';
import {MyTableComponent} from '../../components/my-table/my-table.component';
import {ToastrService} from 'ngx-toastr';
import {ThemePalette} from '@angular/material/core';
import {EmployerService} from '../../services/employer.service';
import {Employee} from '../../models/Employee';
import {Router} from '@angular/router';


@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees-employers.component.html',
  styleUrls: ['./list-employees-employers.component.scss'],
  providers: [EmployeeService, EmployerService]
})
export class ListEmployeesEmployersComponent implements OnInit {
  columnHeader = {'id': 'Id', 'name': 'Name', 'lastName': 'Surname', 'email': 'Email', 'isBoss': 'Is this user boss', 'boss': 'Boss Id'};
  @ViewChild('myChild') private myChild: MyTableComponent;
  tableData: Employee[];
  color: ThemePalette = 'accent';
  checked = false;
  clickedEmployee: Employee;

  constructor(private router: Router, private employeeService: EmployeeService,
              private employersService: EmployerService, private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.loadAllEmployees();
  }

  onChange(event: any) {
    this.checked = !this.checked;
    if (this.checked) {
      this.loadAllEmployers();
    } else {
      this.loadAllEmployees();
    }
  }

  loadAllEmployees() {
    this.employeeService.getAllEmployees().subscribe(response => {
      this.loadDataSuccess(response);
    }, error => {
      this.loadDataFailure();
    });
  }

  loadAllEmployers() {
    this.employersService.getAllEmployers().subscribe(response => {
      this.loadDataSuccess(response);
    }, error => {
      this.loadDataFailure();
    });
  }

  loadDataSuccess(response: any) {
    this.toastr.success('Data fetched succesfully');
    this.tableData = response;
    this.myChild.init(this.tableData);
  }

  loadDataFailure() {
    this.toastr.error('Something went wrong');
  }

  selectedRow(employee: Employee) {
    for (let i = 0; i < this.tableData.length; i++) {
      if (employee.id === this.tableData[i].id) {
        this.clickedEmployee = this.tableData[i];
        break;
      }
    }
    if (this.clickedEmployee.isBoss === true) {
      this.router.navigate(['employerView/' + this.clickedEmployee.id]);
    } else if (this.clickedEmployee.isBoss === false) {
      this.router.navigate(['employeeView/' + this.clickedEmployee.id]);
    }
  }
}
