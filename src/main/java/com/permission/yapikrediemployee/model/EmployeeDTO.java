package com.permission.yapikrediemployee.model;


import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeDTO {

    private String name;

    private String department;
    private LocalDate workStartDate;

    private LocalDate workEndDate;
    private int kalanPermission;
    private Set<Permission> permissions = new HashSet<Permission>(0);
}
