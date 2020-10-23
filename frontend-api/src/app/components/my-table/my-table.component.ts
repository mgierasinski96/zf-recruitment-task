import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Employee} from '../../models/Employee';

@Component({
  selector: 'app-my-table',
  templateUrl: './my-table.component.html',
  styleUrls: ['./my-table.component.scss'],
})
export class MyTableComponent implements OnInit {
  @Input() tableData;
  @Input() columnHeader;
  @Output()
  public selectedRow = new EventEmitter<Object>(); // 1
  objectKeys = Object.keys;
  dataSource;
  @ViewChild(MatSort) sort: MatSort;

  constructor() {
  }

  ngOnInit(): void {

  }

  initializeTable() {
    this.dataSource = new MatTableDataSource(this.tableData);
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  init(data) {
    this.tableData = data; // get data
    this.initializeTable(); // and initlize table
  }

  public rowClicked(employee: Employee): void { // 2
    this.selectedRow.emit(employee);
  }
}
