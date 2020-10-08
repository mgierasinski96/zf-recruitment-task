package com.gierasinski.zfrecruitmenttask.dao;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByBoss_Id(long id);
}
