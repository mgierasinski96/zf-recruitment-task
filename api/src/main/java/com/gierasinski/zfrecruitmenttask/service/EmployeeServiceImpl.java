package com.gierasinski.zfrecruitmenttask.service;

import com.gierasinski.zfrecruitmenttask.dao.EmployeeRepository;
import com.gierasinski.zfrecruitmenttask.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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

}
