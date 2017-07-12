package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Gianina.Carp on 7/12/2017.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetTableNameMethod2() {
        String tableName = EntityUtils.getTableName(Job.class);
        assertEquals("Table name should be jobs!", "jobs", tableName);
    }

    @Test
    public void testGetColumns() {
        ArrayList<ColumnInfo> columns = EntityUtils.getColumns(Department.class);
        assertEquals("The colums should be!", 3, columns.size());
    }

    @Test
    public void testGetColumns2() {
        ArrayList<ColumnInfo> columns = EntityUtils.getColumns(Employee.class);
        assertEquals("The colums should be!", 11, columns.size());
    }

    @Test
    public void testCastFromSqlType(){
        Object object = EntityUtils.castFromSqlType(BigDecimal.valueOf(5), Integer.class);
        System.out.println(Integer.class);
        assertEquals("The type should be Integer!", Integer.class, object.getClass());
    }

    @Test
    public void testCastFromSqlType2(){
        Object object = EntityUtils.castFromSqlType(BigDecimal.valueOf(5), Long.class);
        assertEquals("The type should be Long!", Long.class, object.getClass());
    }

    @Test
    public void testGetFieldsByAnnotations(){
        ArrayList<Field> fields = EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
        assertEquals("The size should be 2!", 2, fields.size());
    }

    @Test
    public void testGetFieldsByAnnotations2(){
        ArrayList<Field> fields = EntityUtils.getFieldsByAnnotations(Employee.class, Column.class);
        assertEquals("The size should be 10!", 10, fields.size());
    }

    @Test
    public void testGetSqlValue(){
        Object object = EntityUtils.getSqlValue(new Department());
        assertEquals("The object should be id!", "id", object);
    }

    @Test
    public void testGetSqlValue2(){
        Object object = EntityUtils.getSqlValue(new Employee());
        assertEquals("The object should be id!", "id", object);
    }
}
