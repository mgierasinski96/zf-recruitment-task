import {Component, Input, OnInit} from '@angular/core';
import {ErrorStateMatcher} from '@angular/material/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {EmployerService} from '../../services/employer.service';
import {EmployeeService} from '../../services/employee.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

export class EmailErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.scss'],
})
export class AddFormComponent implements OnInit {
  @Input() employeeOrEmployer;
  @Input() update;
  @Input() initData;
  registerForm = new FormGroup({
    name: new FormControl('', Validators.pattern('[A-z][A-z]+')),
    lastName: new FormControl('', Validators.pattern('[A-z][A-z]+')),
    email: new FormControl('', Validators.email),
    id: new FormControl()
  });

  constructor(private employeeService: EmployeeService, private router: Router, private route: ActivatedRoute,
              private employerService: EmployerService, private toastr: ToastrService) {
  }

  hasError(controlName) {
    return this.registerForm.get(controlName).hasError;
  }

  ngOnInit(): void {
    this.registerForm.controls['name'].setValue(this.initData?.name);
    this.registerForm.controls['lastName'].setValue(this.initData?.lastName);
    this.registerForm.controls['email'].setValue(this.initData?.email);
    this.registerForm.controls['id'].setValue(this.initData?.id);
  }

  registerEmployeeOrEmployer() {
    if (this.employeeOrEmployer) {
      this.employerService.saveEmployer(this.registerForm.value).subscribe(response => {
        this.toastr.success('Registered employer successfully');
      }, error1 => {
        this.toastr.error('Something went wrong');
      });
    } else {
      this.employeeService.saveEmployee(this.registerForm.value).subscribe(response => {
        this.toastr.success('Registered employee successfully');
      }, error1 => {
        this.toastr.error('Something went wrong');
      });
    }
  }

  updateEmployeeOrEmployer() {
    this.registerForm.controls['id'].setValue(this.initData.id);
    if (this.route.snapshot.routeConfig.path.includes('employerView')) {
      this.employerService.updateEmployer(this.registerForm.value).subscribe(response => {
        this.router.navigate(['listEmployeesEmployers']);
      });
    } else if (this.route.snapshot.routeConfig.path.includes('employeeView')) {
      this.employeeService.updateEmployee(this.registerForm.value).subscribe(response => {
        this.router.navigate(['listEmployeesEmployers']);
      });
    }

  }

  getEmployeeOrEmployerStatus() {
    this.employeeOrEmployer = !this.employeeOrEmployer;
  }
}
