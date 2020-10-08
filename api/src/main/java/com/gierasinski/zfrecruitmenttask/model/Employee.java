package com.gierasinski.zfrecruitmenttask.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name="boss_id")
    private Employer boss;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employer getBoss() {
        return boss;
    }

    public void setBoss(Employer boss) {
        this.boss = boss;
    }
}
