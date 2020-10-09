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
  tableData: Employee[];
  columnHeader = {'id': 'Id', 'name': 'Name', 'lastName': 'Surname'};
  employeeId: any;
  employeeData;
  employerView: boolean;
;
  clickedId;

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

  performAction(event: any) {
    if (event.type !== 'button' && event.nodeName !== 'DIV' && event.nodeName !== 'TH' && event.nodeName !== 'INPUT') {
      this.clickedId = event.target.parentNode.children[0]?.children[0]?.innerText ||
        event.target.parentNode.parentNode?.children[0]?.children[0]?.innerText;
      this.employeeService.getEmployeeById(this.clickedId).subscribe(response => {
        const dialog = this.dialog.open(DialogAddToBossComponent, {
          data: {
            bossName: this.employeeData.name,
            bossLastName: this.employeeData.lastName,
            employeeName: response.name,
            employeeLastName: response.lastName
          }
        });
        dialog.afterClosed().subscribe(result => {
          if (result) {
            this.employeeService.getAndSetEmployeeToNewBoss(this.employeeData.id, response.id).subscribe(response1 => {
              this.createTablesForBoss();
            });
          }
        });
      });
    }
  }

  createTablesForBoss() {
    this.employeeService.getEmployeesWithoutBoss().subscribe(response => {
      this.loadDataSuccess(response, this.myChild1);
    }, error => {
      this.loadDataFailure();
    });
    this.employeeService.getEmployeesForBoss(this.employeeId).subscribe(response => {
      this.loadDataSuccess(response, this.myChild2);
    }, error => {
      this.loadDataFailure();
    });
  }

  loadDataSuccess(response: any, table: MyTableComponent) {
    this.toastr.success('Data fetched succesfully');
    this.tableData = response;
    table.init(this.tableData);
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
