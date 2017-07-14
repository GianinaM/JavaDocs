package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Gianina.Carp on 7/14/2017.
 */
public class DepartmentDao {
    private EntityManagerImpl entityManager = new EntityManagerImpl();

    public Department findDepartmentById(Long id) throws SQLException {
        return this.entityManager.findById(Department.class, id);
    }

    public long getNextItValOfDepartments() throws SQLException {
        return this.entityManager.getNextIdVal("departments", "department_id");
    }

    public Department insertDepartment(Department department) throws SQLException {
        return (Department) this.entityManager.insert(department);
    }

    public List<Department> findAllDepartments() throws SQLException {
        return this.entityManager.findAll(Department.class);
    }

    public Department updateDepartment(Department department) throws SQLException {
        return this.entityManager.update(department);
    }

    public void deleteDepartment(Department department) throws SQLException {
        this.entityManager.delete(department);
    }

    public List<Department> findDepartmentsByParams(Map<String, Object> params) throws SQLException {
        return this.entityManager.findByParams(Department.class, params);
    }
}
