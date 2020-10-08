package com.gierasinski.zfrecruitmenttask.dao;

import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
