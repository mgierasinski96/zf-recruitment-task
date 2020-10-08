package com.gierasinski.zfrecruitmenttask;

import com.gierasinski.zfrecruitmenttask.controller.EmployeeController;
import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.model.Employer;
import com.gierasinski.zfrecruitmenttask.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

// BUSINESS LOGIC TESTS
@RunWith(MockitoJUnitRunner.class)
public class EmployeeBehavioralTests {
    //given
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    //when
    @Before
    public void prepareEmployeesList()
    {
        Mockito.when(employeeService.findAllEmployees()).thenReturn(Arrays.asList(
                new Employee(1,"John", "Spencer","spencer@gmail.com",
                        new Employer(222,"Mark", "Rogers", "boss@gmail.com", null)),
                new Employee(3,"Martin", "Liroy","liroy@gmail.com", null),
                new Employee(4,"Stacey", "Robe","stacey.robe@gmail.com", null),
                new Employee(5,"Timothy", "Mercy","mercy82@yahoo.com", new Employer())
        ));
    }

    @Test
    public void check_get_all_employees()
    {
        List<Employee> allExpectedEmployees=Arrays.asList(
                new Employee(1,"John", "Spencer","spencer@gmail.com",
                        new Employer(222,"Mark", "Rogers", "boss@gmail.com", null)),
                new Employee(3,"Martin", "Liroy","liroy@gmail.com", null),
                new Employee(4,"Stacey", "Robe","stacey.robe@gmail.com", null),
                new Employee(5,"Timothy", "Mercy","mercy82@yahoo.com", new Employer())

        );

        //then
        Assert.assertEquals(allExpectedEmployees.get(0), employeeController.getAllEmployees().get(0)); //check first employee
        Assert.assertArrayEquals(allExpectedEmployees.toArray(), employeeController.getAllEmployees().toArray()); // check all employees
        Assert.assertThat(employeeController.getAllEmployees(), Matchers.hasSize(allExpectedEmployees.size())); // check number of employees


        Mockito.verify(employeeService, Mockito.times(3)).findAllEmployees();
    }

    @Test
    public void check_get_employees_without_boss()
    {
        List<Employee> allExpectedEmployees=Arrays.asList(
                new Employee(3,"Martin", "Liroy","liroy@gmail.com", null),
                new Employee(4,"Stacey", "Robe","stacey.robe@gmail.com", null)
        );

        //then
        Assert.assertEquals(allExpectedEmployees.get(0), employeeController.getEmployeesWithoutBoss().get(0)); //check first employee
        Assert.assertArrayEquals(allExpectedEmployees.toArray(), employeeController.getEmployeesWithoutBoss().toArray()); // check all employees
        Assert.assertThat(employeeController.getEmployeesWithoutBoss(), Matchers.hasSize(allExpectedEmployees.size())); // check number of employees


        Mockito.verify(employeeService, Mockito.times(3)).findAllEmployees();
    }
}
