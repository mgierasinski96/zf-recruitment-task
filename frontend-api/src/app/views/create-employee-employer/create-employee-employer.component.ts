import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {EmployeeService} from '../../services/employee.service';
import {ThemePalette} from '@angular/material/core';
import {EmployerService} from '../../services/employer.service';
import {AddFormComponent} from '../../components/add-form/add-form.component';


@Component({
  selector: 'app-list-employees',
  templateUrl: './create-employee-employer.component.html',
  styleUrls: ['./create-employee-employer.component.scss'],
})
export class CreateEmployeeEmployerComponent implements OnInit {
  @ViewChild('myChild') private myChild: AddFormComponent;
  color: ThemePalette = 'accent';
  checked = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  onChange(event: any) {
    this.checked = !this.checked;
    this.myChild.getEmployeeOrEmployerStatus();

  }
}
