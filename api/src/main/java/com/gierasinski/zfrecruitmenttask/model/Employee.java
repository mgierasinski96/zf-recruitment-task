package com.gierasinski.zfrecruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "is_boss")
@DiscriminatorValue(value = "false")
public class Employee {

    public Employee() {
    }

    public Employee(long id, String name, String lastName, String email, Employer boss) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.boss = boss;
    }

    public Employee(@NotBlank(message = "field name cannot be empty") String name, String lastName, String email, Employer boss) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.boss = boss;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "field name cannot be empty")
    private String name;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Employer boss;

    @Column(name = "is_boss", insertable = false, updatable = false)
    protected boolean isBoss;

    public boolean getIsBoss() {
        return isBoss;
    }

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

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        return boss != null ? boss.equals(employee.boss) : employee.boss == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (boss != null ? boss.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", boss=" + boss +
                '}';
    }
}
