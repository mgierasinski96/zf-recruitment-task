package com.gierasinski.zfrecruitmenttask.service;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.model.Employer;

import java.util.List;


public interface EmployerService {

    Employer findById(long id);

    void deleteEmployer(long id);

    List<Employer> findAllEmployers();

    Employer saveEmployer(Employer employer);
}
