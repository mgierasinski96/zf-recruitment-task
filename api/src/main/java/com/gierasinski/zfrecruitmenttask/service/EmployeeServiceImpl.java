package com.gierasinski.zfrecruitmenttask.service;

import com.gierasinski.zfrecruitmenttask.dao.EmployeeRepository;
import com.gierasinski.zfrecruitmenttask.dao.EmployerRepository;
import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployerRepository employerRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployerRepository employerRepository) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.delete(employeeRepository.getOne(id));
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmployeesByBossId(long id) {
        return employeeRepository.findAllByBoss_Id(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee setBossToNull(long id) {
        Employee employee = employeeRepository.getOne(id);
        employee.setBoss(null);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee setBossToEmployee(long bossId, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        Employer employer = employerRepository.getOne(bossId);
        employee.setBoss(employer);
        return employeeRepository.save(employee);
    }


}
