package com.gierasinski.zfrecruitmenttask.controller;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.findById(id);
    }

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PutMapping(value = "")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping(value = "")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @DeleteMapping(value = "")
    void deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
    }
    @GetMapping(value = "/forboss/{id}")
    public List<Employee> findEmployeesForBoss(@PathVariable("id") long id) {
        return employeeService.findEmployeesByBossId(id);
    }

    @GetMapping(value = "/allWithoutBoss")
    public List<Employee> findEmployeesWithoutBoss()
    {
        return employeeService.findAllEmployees().stream().filter(employee -> employee.getBoss() == null).collect(Collectors.toList());
    }
}
