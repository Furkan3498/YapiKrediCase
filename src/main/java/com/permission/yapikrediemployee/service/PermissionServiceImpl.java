package com.permission.yapikrediemployee.service;

import com.permission.yapikrediemployee.Exception.EmployeeException;
import com.permission.yapikrediemployee.Exception.EventException;
import com.permission.yapikrediemployee.model.Permission;
import com.permission.yapikrediemployee.model.PermissionDTO;

import java.util.List;

public interface PermissionServiceImpl {
    public List<Permission> createeEvent(PermissionDTO eventDTO) throws EventException, EmployeeException;

    public Boolean updateEvent(PermissionDTO eventDTO, Integer eventId) throws EventException, EmployeeException;

    public Permission deleteEvent(Long eventId) throws EventException, EmployeeException;
}
