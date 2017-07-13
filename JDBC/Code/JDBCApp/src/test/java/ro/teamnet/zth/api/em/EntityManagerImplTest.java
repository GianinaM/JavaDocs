package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public class EntityManagerImplTest {
    @Test
    public void testFindById() throws SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department myT = entityManager.findById(Department.class, (long) 70);
        assertEquals("It should return Public Relation", "Public Relations", myT.getDepartmentName());
    }

    @Test
    public void testGetNextIdVal() throws SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        long myLong = entityManager.getNextIdVal("departments", "department_id");
        assertEquals("It should return 271", 274, myLong);
    }

    @Test
    public void testInsert() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("Baietii de la IT");
        department.setLocation(1000L);
        Department result = (Department) entity.insert(department);
        assertEquals("It should return Baietii de la IT", "Baietii de la IT", result.getDepartmentName());
    }

    @Test
    public void testFindAll() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        List<Department> allDepartments = entity.findAll(Department.class);
        assertEquals("It should return 31", 31, allDepartments.size());
    }

    @Test
    public void testUpdate() throws SQLException {
        EntityManagerImpl entity = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("Now we are girls from IT");
        department.setId(272L);
        department.setLocation(1000L);
        Department result = entity.update(department);
        assertEquals("It should return Now we are girls from IT", "Now we are girls from IT", result.getDepartmentName());
    }

}
