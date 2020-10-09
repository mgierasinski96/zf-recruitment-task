package com.gierasinski.zfrecruitmenttask;

import com.gierasinski.zfrecruitmenttask.dao.EmployeeRepository;
import com.gierasinski.zfrecruitmenttask.model.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EmployeeIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeById() {

        Employee employee = new Employee("John", "Ruseel", "jrsu@gmail.com", null);
        entityManager.persist(employee);
        entityManager.flush();

        Employee foundEmployee = employeeRepository.getOne(employee.getId());
        Assert.assertEquals(employee, foundEmployee);
    }

    @Test
    public void getAllEmployees() {

        Employee employee = new Employee("John", "Ruseel", "jrsu@gmail.com", null);
        Employee employee1 = new Employee("Steve", "Johnson", "johnson@gmail.com", null);
        entityManager.persist(employee);
        entityManager.persist(employee1);
        entityManager.flush();

        List<Employee> expected = Arrays.asList(employee, employee1);
        List<Employee> foundEmployees = employeeRepository.findAll();
        Assert.assertEquals(expected.size(), foundEmployees.size());
        Assert.assertArrayEquals(expected.toArray(), foundEmployees.toArray());
    }

}
