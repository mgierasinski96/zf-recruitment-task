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
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, EmployeeRepository employeeRepository) {
        this.employerRepository = employerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employer findById(long id) {
        return employerRepository.getOne(id);
    }

    @Override
    public void deleteEmployer(long id) {
        Employer employer = employerRepository.getOne(id);
        employer.getEmployeesManaged().stream().forEach(employee -> // dont allow to delete cascade
        {
            employee.setBoss(null);
            employeeRepository.save(employee);
        });
        employerRepository.delete(employer);
    }

    @Override
    public List<Employer> findAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

}
