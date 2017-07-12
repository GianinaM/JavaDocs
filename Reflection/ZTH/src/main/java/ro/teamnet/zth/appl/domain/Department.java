package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Gianina.Carp on 7/12/2017.
 */
@Table(name = "departments")
public class Department {
    @Id(name = "department_id")
    private Long id;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "location")
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (!getId().equals(that.getId())) return false;
        if (!getDepartmentName().equals(that.getDepartmentName())) return false;
        return getLocation().equals(that.getLocation());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", location=" + location +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {

        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Location getLocation() {
        return location;
    }
}
