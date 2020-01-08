package com.example.springboot.datademo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
        @Autowired
        EmployeeRepository repository;
        
        public List<Employee> getAllEmployees(){
        	List<Employee> employeeList = repository.findAll();
        	if(employeeList.size()>0) {
        		return employeeList;
        	}
        	else {
        		return new ArrayList<Employee>();
        	}
        }
        
        public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        	Optional<Employee> employee = repository.findById(id);
        	if(employee.isPresent()) {
        		return employee.get();
        	}
        	else {
        		throw new RecordNotFoundException("No employee record exist for given id");
        	}
        }
        
        public Employee createOrUpdateEmployee(Employee employee) throws RecordNotFoundException {
        	Optional<Employee> employeeEntity = repository.findById(employee.getId());
        	if(employeeEntity.isPresent()) {
        		Employee newEmployee = employeeEntity.get();
        		newEmployee.setEmail(employee.getEmail());
        		newEmployee.setFirstname(employee.getFirstname());
        		newEmployee = repository.save(newEmployee);
        		return newEmployee;
        	}
        	else {
        		employee = repository.save(employee);
        		return employee;
        	}
        }
        
        public void deleteEmployeeById(Long id) throws RecordNotFoundException 
        {
            Optional<Employee> employee = repository.findById(id);
             
            if(employee.isPresent()) 
            {
                repository.deleteById(id);
            } else {
                throw new RecordNotFoundException("No employee record exist for given id");
            }
        } 
}
