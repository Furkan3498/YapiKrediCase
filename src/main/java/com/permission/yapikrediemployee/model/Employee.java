package com.permission.yapikrediemployee.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
public class Employee {
    @Id
    private long id;

    private String name;

    private String department;
    private LocalDate workStartDate;

    private LocalDate workEndDate;
    private int kalanPermission;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "permissionid")})
    private Set<Permission> permissions = new HashSet<Permission>(0);

    public Employee(long id, String name, String department, LocalDate workStartDate, LocalDate workEndDate, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.workStartDate = workStartDate;
        this.workEndDate = workEndDate;
        this.permissions = permissions;
    }

    public Employee() {
    }


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_permission", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "permissionid")})
    public Set<Permission> getPermissions() {
        return this.permissions;
    }


    public boolean hasPermission(Permission permission) {
        for (Permission employeePermission : getPermissions()) {
            if (employeePermission.getPermissionid() == permission.getPermissionid()) {
                return true;
            }
        }
        return false;
    }

    public Integer izinGunu(){
        LocalDate ldt = LocalDate.of(workEndDate.getYear(),workEndDate.getMonth(),workEndDate.getDayOfMonth());
        LocalDate ldtt = LocalDate.of(workStartDate.getYear(),workStartDate.getMonth(),workStartDate.getDayOfMonth());
        int i = ldt.compareTo(ldtt);
        if (i < 365){
            kalanPermission= 5;
        } else if (i> 366 && i<2190) {
        kalanPermission = 15;
        } else if (i> 2190 && i<4015) {
        kalanPermission= 18;
        }else kalanPermission=24;
        return kalanPermission;
    }

}
