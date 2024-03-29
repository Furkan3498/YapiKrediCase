package com.permission.yapikrediemployee.service;

import com.permission.yapikrediemployee.Exception.EmployeeException;
import com.permission.yapikrediemployee.model.Employee;
import com.permission.yapikrediemployee.model.EmployeeDTO;
import com.permission.yapikrediemployee.model.Permission;
import org.w3c.dom.events.EventException;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {
    public Employee addEmployess(EmployeeDTO userDTO) throws EmployeeException;


    public Optional<Employee> editEmployee(Long employeeId,EmployeeDTO employeeDTO) throws EmployeeException;
}
