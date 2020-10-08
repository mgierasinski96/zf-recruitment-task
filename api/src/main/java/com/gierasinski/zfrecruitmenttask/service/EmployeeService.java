package com.gierasinski.zfrecruitmenttask.service;

import com.gierasinski.zfrecruitmenttask.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(long id);
    void deleteEmployee(long id);
    List<Employee> findAllEmployees();
    List<Employee> findEmployeesByBossId(long id);
    Employee saveEmployee(Employee employee);
}
