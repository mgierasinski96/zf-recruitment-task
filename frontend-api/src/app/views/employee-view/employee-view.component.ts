import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {EmployeeService} from '../../services/employee.service';
import {ThemePalette} from '@angular/material/core';
import {EmployerService} from '../../services/employer.service';
import {AddFormComponent} from '../../components/add-form/add-form.component';
import {ActivatedRoute, Router} from '@angular/router';
import {MyTableComponent} from '../../components/my-table/my-table.component';
import {Employee} from '../../models/Employee';
import {ToastrService} from 'ngx-toastr';
import {MatDialog} from '@angular/material/dialog';
import {DialogAddToBossComponent} from '../../components/user-dialogs/add-to-boss.component';


@Component({
  selector: 'app-employee-view',
  templateUrl: './employee-view.component.html',
  styleUrls: ['./employee-view.component.scss'],
  providers: [EmployeeService]
})
export class EmployeeViewComponent implements OnInit {
  @ViewChild('myChild') private myChild: AddFormComponent;
  @ViewChild('myChild1') private myChild1: MyTableComponent;
  @ViewChild('myChild2') private myChild2: MyTableComponent;
  myChild1TableData: Employee[];
  myChild2TableData: Employee[];
  columnHeader = {'id': 'Id', 'name': 'Name', 'lastName': 'Surname'};
  employeeId: any;
  employeeData;
  employerView: boolean;

  constructor(private employeeService: EmployeeService, private dialog: MatDialog, private router: Router,
              private employerService: EmployerService, private route: ActivatedRoute, private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.paramMap.get('id');
    this.employeeService.getEmployeeById(this.employeeId).subscribe(response => {
      this.employeeData = response;
    });
    if (this.route.snapshot.routeConfig.path.includes('employerView')) {
      this.employerView = true;
      this.createTablesForBoss();
    } else {
      this.employerView = false;
    }
  }

  setBossToNull() {
    this.employeeService.setBossToNull(this.employeeId).subscribe(response => {
      this.employeeData = response;
    });
  }

  selectedRow(employee: Employee) {
    let worker: Employee;
    for (let i = 0; i < this.myChild1TableData.length; i++) {
      if (employee.id === this.myChild1TableData[i].id) {
        worker = this.myChild1TableData[i];
        break;
      }
    }
    const dialog = this.dialog.open(DialogAddToBossComponent, {
      data: {
        bossName: this.employeeData.name,
        bossLastName: this.employeeData.lastName,
        employeeName: worker.name,
        employeeLastName: worker.lastName
      }
    });
    dialog.afterClosed().subscribe(result => {
      if (result) {
        this.employeeService.getAndSetEmployeeToNewBoss(this.employeeData.id, worker.id).subscribe(response1 => {
          this.createTablesForBoss();
        });
      }
    });
  }

  createTablesForBoss() {
    this.employeeService.getEmployeesWithoutBoss().subscribe(response => {
      this.myChild1TableData = response;
      this.loadDataSuccess(this.myChild1TableData, this.myChild1);
    }, error => {
      this.loadDataFailure();
    });
    this.employeeService.getEmployeesForBoss(this.employeeId).subscribe(response => {
      this.myChild2TableData = response;
      this.loadDataSuccess(this.myChild2TableData, this.myChild2);
    }, error => {
      this.loadDataFailure();
    });
  }

  loadDataSuccess(tableData: any, table: MyTableComponent) {
    this.toastr.success('Data fetched succesfully');
    table.init(tableData);
  }

  loadDataFailure() {
    this.toastr.error('Something went wrong');
  }

  deleteWholeEntity() {
    if (this.employerView) {
      this.employerService.deleteEmployer(this.employeeData.id).subscribe(response => {
        this.router.navigate(['listEmployeesEmployers']);
        this.toastr.success('Employer succesfully deleted');
      });

    } else {
      this.employeeService.deleteEmployee(this.employeeData.id).subscribe(response => {
        this.router.navigate(['listEmployeesEmployers']);
        this.toastr.success('Employee succesfully deleted');
      });
    }
  }
}
