package com.gierasinski.zfrecruitmenttask.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends Employee{

    @OneToMany(mappedBy = "boss", fetch = FetchType.LAZY)
    private List<Employee> employeesManaged = new ArrayList<>();

    public List<Employee> getEmployeesManaged() {
        return employeesManaged;
    }

    public void setEmployeesManaged(List<Employee> employeesManaged) {
        this.employeesManaged = employeesManaged;
    }

    public Employer() {
    }

    public Employer(long id, String name, String lastName, String email, Employer boss) {
        super(id,name, lastName, email, boss);
    }
}
