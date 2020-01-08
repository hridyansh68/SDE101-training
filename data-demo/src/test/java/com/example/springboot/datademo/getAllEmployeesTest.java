package com.example.springboot.datademo;



import net.minidev.json.JSONObject;
import org.assertj.core.internal.bytebuddy.description.method.MethodDescription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class getAllEmployeesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    private final String URL = "/employees/";

    @Test
    public void getAllEmployeeTest() throws Exception {

        List<Employee> employeeList = buildEmployeeList();
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        String empListResult = mvcResult.getResponse().getContentAsString();
        assertNotNull(empListResult, "Employees not found");

    }

    private List<Employee> buildEmployeeList(){
        Employee e1 = new Employee(1L,"harry","hridyansh68@gmail.com");
        Employee e2 = new Employee(2L,"rohan","rohan@gmail.com");
        List<Employee> emplist = Arrays.asList(e1,e2);


        return emplist;

    }
}
