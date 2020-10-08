package com.gierasinski.zfrecruitmenttask.controller;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
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
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @DeleteMapping(value = "")
    void deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
    }
    @ApiOperation(value = "returns list of employess that are managed by specific boss")
    @GetMapping(value = "/forboss/{id}")
    public List<Employee> getEmployeesForBoss(@ApiParam(value= "boss id", example = "1") @PathVariable("id") long id) {
        return employeeService.findEmployeesByBossId(id);
    }

    @ApiOperation(value = "returns list of employees that don't have boss")
    @GetMapping(value = "/allWithoutBoss")
    public List<Employee> getEmployeesWithoutBoss()
    {
        return employeeService.findAllEmployees().stream().filter(employee -> employee.getBoss() == null).collect(Collectors.toList());
    }
}
