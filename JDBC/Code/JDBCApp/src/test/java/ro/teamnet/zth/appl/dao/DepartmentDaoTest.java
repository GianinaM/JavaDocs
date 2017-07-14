package ro.teamnet.zth.appl.dao;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Gianina.Carp on 7/14/2017.
 */
public class DepartmentDaoTest {
    long nextVal = 0L;
    private DepartmentDao depDao = new DepartmentDao();

    @Test
    public void testFindDepartmentById() throws SQLException {
        Department myT = depDao.findDepartmentById((long) 70);
        assertEquals("It should return Public Relation", "Public Relations", myT.getDepartmentName());
    }

    @Test
    public void testGetNextItValOfDepartments() throws SQLException {
        long myLong = depDao.getNextItValOfDepartments();
        assertEquals("It should return 287", 287, myLong);
    }

    @Test
    public void testInsertDepartment() throws SQLException {
        this.nextVal = depDao.getNextItValOfDepartments();
        Department department = new Department();
        department.setDepartmentName("Other New Department");
        department.setLocation(1000L);
        Department result = (Department) depDao.insertDepartment(department);
        assertEquals("It should return Other New Department", "Other New Department", result.getDepartmentName());
    }

    @Test
    public void testFindAllDepartments() throws SQLException {
        List<Department> allLocations = depDao.findAllDepartments();
        assertEquals("It should return 42", 42, allLocations.size());
    }

    @Test
    public void testUpdateDepartment() throws SQLException {
        Department department = depDao.findDepartmentById(270L);
        department.setDepartmentName("IT Girls");
        Department result = depDao.updateDepartment(department);
        assertEquals("It should return IT Girls", "IT Girls", result.getDepartmentName());
    }

    @Test
    public void testDeleteDepartment() throws SQLException {
        Department department = depDao.findDepartmentById(250L);
        depDao.deleteDepartment(department);
        assertNull(depDao.findDepartmentById(nextVal));
    }

    @Test
    public void testFindDepartmentsByParams() throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("location_id", 1000L);
        params.put("department_name", new String("Baietii de la IT"));
        List<Department> departments = depDao.findDepartmentsByParams(params);
        assertEquals(10, departments.size());
    }
}
