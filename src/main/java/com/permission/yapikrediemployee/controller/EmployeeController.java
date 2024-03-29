package com.permission.yapikrediemployee.controller;

import com.permission.yapikrediemployee.Exception.EmployeeException;
import com.permission.yapikrediemployee.Exception.EventException;
import com.permission.yapikrediemployee.model.Employee;
import com.permission.yapikrediemployee.model.EmployeeDTO;
import com.permission.yapikrediemployee.model.Permission;
import com.permission.yapikrediemployee.model.PermissionDTO;
import com.permission.yapikrediemployee.service.EmployeeService;
import com.permission.yapikrediemployee.service.EmployeeServiceInterface;
import com.permission.yapikrediemployee.service.PermissionService;
import com.permission.yapikrediemployee.service.PermissionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PermissionService permissionService;
    private PermissionServiceImpl permissionServiceIm;
    private EmployeeServiceInterface employeeServiceInterface;


    public EmployeeController(EmployeeService employeeService, PermissionService permissionService, PermissionServiceImpl permissionServiceIm) {
        this.employeeService = employeeService;
        this.permissionService = permissionService;
        this.permissionServiceIm = permissionServiceIm;
    }

   @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployee() {
       return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping( "/add")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeException {
        return (employeeServiceInterface.addEmployess(employeeDTO));

    }

  @PutMapping("/edit/{id}")
    public Optional<Employee> editEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) throws EmployeeException {
        return employeeServiceInterface.editEmployee(employeeId,employeeDTO );
  }
    @DeleteMapping( "/delete/{id}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);

    }

    @PostMapping("/create")
    public ResponseEntity<List<Permission>> createEvent(@Valid @RequestBody PermissionDTO permissionDTO) throws EventException, EmployeeException {
        return new ResponseEntity<List<Permission>>(permissionServiceIm.createeEvent(permissionDTO), HttpStatus.CREATED);
    }


    @PostMapping(value = "/employee/{id}/permissions")
    public Permission employeesAddPermission( @PathVariable Long id, @RequestParam Long permissionId, PermissionDTO permission) throws EmployeeException, EventException {
       return permissionService.permissionToEmploye(id , permissionId,permission);
    }

}
