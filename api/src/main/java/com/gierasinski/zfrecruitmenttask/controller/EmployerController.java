package com.gierasinski.zfrecruitmenttask.controller;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.model.Employer;
import com.gierasinski.zfrecruitmenttask.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employer")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(value = "/{id}")
    public Employer getEmployerById(@PathVariable("id") long id) {
        return employerService.findById(id);
    }

    @GetMapping(value = "")
    public List<Employer> getAllEmployers() {
        return employerService.findAllEmployers();
    }

    @PutMapping(value = "")
    public Employer updateEmployer(@RequestBody Employer employer) {
        return employerService.saveEmployer(employer);
    }

    @PostMapping(value = "")
    public Employee saveEmployer(@RequestBody Employer employer) {
        return employerService.saveEmployer(employer);
    }
    @DeleteMapping(value = "")
    void deleteEmployer(@RequestParam long id){
        employerService.deleteEmployer(id);
    }

}
