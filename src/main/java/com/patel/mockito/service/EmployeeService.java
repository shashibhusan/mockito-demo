package com.patel.mockito.service;

import com.patel.mockito.model.Employee;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee) throws NotFoundException;

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(long id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long id);

    Optional<Employee> getEmployeeByEmailId(String emailId);
}
