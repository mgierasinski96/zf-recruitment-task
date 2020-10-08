package com.gierasinski.zfrecruitmenttask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gierasinski.zfrecruitmenttask.controller.EmployeeController;
import com.gierasinski.zfrecruitmenttask.model.Employee;
import com.gierasinski.zfrecruitmenttask.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerStatusTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void post_employee_with_VALID_fields() throws Exception {
        Employee employee = new Employee();
        employee.setName("Mark"); // name is required


        mvc.perform(post("/employee")
                .contentType(APPLICATION_JSON)
                .content(toJson(employee)))
                .andExpect(status().isOk());
    }

    @Test
    public void post_employee_with_INVALID_fields() throws Exception {
        Employee employee = new Employee();

        mvc.perform(post("/employee")
                .contentType(APPLICATION_JSON)
                .content(toJson(employee)))
                .andExpect(status().is(400)); // BAD REQUEST
    }
    private String toJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}
