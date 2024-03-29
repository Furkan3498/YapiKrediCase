package com.permission.yapikrediemployee.service;

import com.permission.yapikrediemployee.Exception.EmployeeException;
import com.permission.yapikrediemployee.model.Employee;
import com.permission.yapikrediemployee.model.EmployeeDTO;
import com.permission.yapikrediemployee.model.Permission;
import com.permission.yapikrediemployee.repository.EmployeeRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private final EmployeeRepo employeeRepo;


    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public boolean hasPermission(Permission permission) {
        Employee ne = new Employee();

        for (Permission employeePermission : ne.getPermissions()) {
            if (employeePermission.getPermissionid() == permission.getPermissionid()) {
                return true;
            }
        }
        return false;
    }


    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepo.findAll();

        return employees;


    }



    public Optional<Employee> editEmployee(Long employeeId, Employee employee) {
        Optional<Employee> employee1 = employeeRepo.findById(employeeId);
       return employee1;

    }



    public void deleteById(Long employeeId) {

         employeeRepo.deleteById(employeeId);
    }


    @Override
    public Employee addEmployess(EmployeeDTO employeeDTO) throws EmployeeException {

        Employee employee = new Employee();
       employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setWorkStartDate(employeeDTO.getWorkStartDate());
        employee.setWorkEndDate(employeeDTO.getWorkEndDate());
        employee.setKalanPermission(employeeDTO.getKalanPermission());
        employee.setPermissions(employeeDTO.getPermissions());

        return employeeRepo.save(employee);
    }

    @Override
    public Optional<Employee> editEmployee(Long employeeId,EmployeeDTO employeeDTO) throws EmployeeException {
        Optional<Employee> employee1 = employeeRepo.findById(employeeId);
        return employee1;
    }
}
