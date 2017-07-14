package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.*;

import static javafx.scene.input.KeyCode.T;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public class EntityManagerImplTest {
    long all = 39L;
    long nextVal = 0L;
    @Test
    public void testFindById() throws SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department myT = entityManager.findById(Department.class, (long) 70);
        assertEquals("It should return Public Relation", "Public Relations", myT.getDepartmentName());
    }

    @Test
    public void testGetNextIdVal() throws SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        long myLong = entityManager.getNextIdVal("locations", "location_id");
        assertEquals("It should return 282", 3201, myLong);
    }


    @Test
    public void testFindAll() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Location> allLocations = entity.findAll(Location.class);
        assertEquals("It should return 31", 23, allLocations.size());
    }

    @Test
    public void testInsert() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        this.nextVal = entity.getNextIdVal("departments", "department_id");
        Department department = new Department();
        department.setDepartmentName("New Department");
        department.setLocation(1000L);
        Department result = (Department) entity.insert(department);
        assertEquals("It should return New Department", "New Department", result.getDepartmentName());
    }

    @Test
    public void testUpdate() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = new Department();
        department = entity.findById(department.getClass(), 270L);
        department.setDepartmentName("Now we are girls from IT");
        Department result = entity.update(department);
        assertEquals("It should return Now we are girls from IT", "Now we are girls from IT", result.getDepartmentName());
    }

    @Test
    public void testDelete() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = entity.findById(Department.class, 260L);
        entity.delete(department);
        assertNull(entity.findById(Department.class, nextVal));
    }

    @Test
    public void testFindByParams() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Map<String, Object> params = new HashMap<>();
        params.put("location_id", 1000L);
        params.put("department_name", new String("Baietii de la IT"));
        List<Department> departments = entity.findByParams(Department.class, params);

        assertEquals(10, departments.size());
    }

    @Test
    public void testFindEmployeesByDepartment() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Employee> employees = entity.findEmployeesByDepartment("str");
        assertEquals(1, employees.size());
    }

    @Test
    public void testFindEmployeesByDepartment2() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Employee> employees = entity.findEmployeesByDepartment("TIon");
        assertEquals(2, employees.size());
    }

    @Test
    public void testInsertTransaction() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = new Department();
        this.nextVal = entity.getNextIdVal("departments", "department_id");
        department.setDepartmentName("First Department");
        department.setLocation(1000L);
        department.setId(this.nextVal);
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        Department department2 = new Department();
        department2.setDepartmentName("Second Department");
        department2.setLocation(1000L);
        department2.setId(this.nextVal + 1);
        departmentList.add(department2);
        entity.insertTransaction(departmentList);
        assertEquals( 44, entity.findAll(Department.class).size());
    }

}
