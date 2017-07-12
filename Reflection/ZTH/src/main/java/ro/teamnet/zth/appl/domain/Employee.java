package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.util.Date;

/**
 * Created by Gianina.Carp on 7/12/2017.
 */
@Table(name = "employees")
public class Employee {
    @Id(name = "employee_id")
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phone_number;

    @Column(name = "hire_date")
    private Date hire_date;

    @Column(name = "job_id")
    private Long job_id;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "commission_pct")
    private Long commission_pct;

    @Column(name = "manager_id")
    private Long manager_id;

    @Column(name = "department_id")
    private Long department_id;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number=" + phone_number +
                ", hire_date=" + hire_date +
                ", salary=" + salary +
                ", commission_pct=" + commission_pct +
                ", manager_id=" + manager_id +
                ", department_id=" + department_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!getId().equals(employee.getId())) return false;
        if (!getFirst_name().equals(employee.getFirst_name())) return false;
        if (!getLast_name().equals(employee.getLast_name())) return false;
        if (!getEmail().equals(employee.getEmail())) return false;
        if (!getPhone_number().equals(employee.getPhone_number())) return false;
        if (!getHire_date().equals(employee.getHire_date())) return false;
        if (!getSalary().equals(employee.getSalary())) return false;
        if (!getCommission_pct().equals(employee.getCommission_pct())) return false;
        if (!getManager_id().equals(employee.getManager_id())) return false;
        return getDepartment_id().equals(employee.getDepartment_id());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFirst_name().hashCode();
        result = 31 * result + getLast_name().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPhone_number().hashCode();
        result = 31 * result + getHire_date().hashCode();
        result = 31 * result + getSalary().hashCode();
        result = 31 * result + getCommission_pct().hashCode();
        result = 31 * result + getManager_id().hashCode();
        result = 31 * result + getDepartment_id().hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCommission_pct() {
        return commission_pct;
    }

    public void setCommission_pct(Long commission_pct) {
        this.commission_pct = commission_pct;
    }

    public Long getManager_id() {
        return manager_id;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
}
