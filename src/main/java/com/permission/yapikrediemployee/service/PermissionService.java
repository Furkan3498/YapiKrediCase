package com.permission.yapikrediemployee.service;

import com.permission.yapikrediemployee.Exception.EmployeeException;
import com.permission.yapikrediemployee.Exception.EventException;
import com.permission.yapikrediemployee.model.Employee;
import com.permission.yapikrediemployee.model.Permission;
import com.permission.yapikrediemployee.model.PermissionDTO;
import com.permission.yapikrediemployee.repository.EmployeeRepo;
import com.permission.yapikrediemployee.repository.PermissionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermissionService implements PermissionServiceImpl {

    private final PermissionRepo permissionRepo;
    private final EmployeeRepo employeeRepo;
    private final EmployeeService employeeService;

    public PermissionService(PermissionRepo permissionRepo, EmployeeRepo employeeRepo, EmployeeService employeeService) {
        this.permissionRepo = permissionRepo;
        this.employeeRepo = employeeRepo;


        this.employeeService = employeeService;
    }

    public boolean updateEvent(PermissionDTO eventDTO, Long eventId) throws EventException, EmployeeException {
        boolean c = false;
        return c;
    }
    @Override
    public List<Permission> createeEvent(PermissionDTO eventDTO) throws EventException, EmployeeException {

        Employee employee = new Employee();

        if (eventDTO == null)
            throw new EventException("Event data can't be null");

        LocalDate eventStartDate = eventDTO.getStartDate();
        long daysBetween = ChronoUnit.DAYS.between(eventDTO.getStartDate(), eventDTO.getEndDate());

        if (daysBetween < 0)
            throw new EventException("Invalid endDate!");

        List<Permission> newEventList = new ArrayList<>();

        for (int i = 0; i < daysBetween; i++) {
            Permission newEvent = new Permission();

            newEvent.setName(eventDTO.getName());
            newEvent.setDescription(eventDTO.getDescription());
            newEvent.setStartTime(eventDTO.getStartTime());
            newEvent.setEndTime(eventDTO.getEndTime());
            newEvent.setStartDate(eventStartDate.plusDays(i));
            newEvent.setEndDate(eventStartDate.plusDays(i + 1));

            employee.getPermissions().add(newEvent);
            permissionRepo.save(newEvent);
            newEventList.add(newEvent);
        }

        return newEventList;
    }

    @Override
    public Boolean updateEvent(PermissionDTO eventDTO, Integer eventId) throws EventException, EmployeeException {
        return null;
    }



    public Permission deleteEvent(Long eventId) throws EventException, EmployeeException {

        Optional<Permission> exixtingEvent = permissionRepo.findById(eventId);


        Employee user = new Employee();

        Permission deletedEvent = null;

        List<Permission> eventList = (List<Permission>) user.getPermissions();
        for (int i = 0; i < eventList.size(); i++) {
            Permission event = eventList.get(i);
            if (event.getPermissionid() == eventId) {
                deletedEvent = event;
                eventList.remove(i);
            }
        }

        user.setPermissions((Set<Permission>) eventList);
        employeeRepo.save(user);
        return deletedEvent;

    }

    public static LocalDate getWorkEndDate() {
        Employee employee = new Employee();
        Permission permission = new Permission();
        return employee.getWorkEndDate();
    }

    public static LocalDate getWorkStartDate() {
        Employee employee = new Employee();
        Permission permission = new Permission();
        return employee.getWorkStartDate();
    }

    public Permission permissionToEmploye(Long permissionId, Long id, PermissionDTO permissionDTO) throws EmployeeException, EventException {



        Employee ee = new Employee();
        Optional<Permission> permisson = permissionRepo.findById(permissionId);
        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isPresent()) {
            if (ee.izinGunu()>0) {
                createeEvent(permissionDTO);

            }

            return deleteEvent(permissionId);
        }

        return deleteEvent(permissionId);
    }





    public List<Permission> createEvent(PermissionDTO eventDTO) throws EventException {


        if (eventDTO == null)
            throw new EventException("Event data can't be null");

        LocalDate eventStartDate = eventDTO.getStartDate();
        long daysBetween = ChronoUnit.DAYS.between(eventDTO.getStartDate(), eventDTO.getEndDate());

        if (daysBetween < 0)
            throw new EventException("Invalid endDate!");

        List<Permission> newEventList = new ArrayList<>();

        for (int i = 0; i < daysBetween; i++) {
            Permission newEvent = new Permission();

            newEvent.setName(eventDTO.getName());
            newEvent.setDescription(eventDTO.getDescription());
            newEvent.setStartTime(eventDTO.getStartTime());
            newEvent.setEndTime(eventDTO.getEndTime());
            newEvent.setStartDate(eventStartDate.plusDays(i));
            newEvent.setEndDate(eventStartDate.plusDays(i + 1));


            permissionRepo.save(newEvent);

            newEventList.add(newEvent);
        }

        return newEventList;

    }


}
