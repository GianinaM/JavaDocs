package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id) throws SQLException;
    long getNextIdVal(String tableName, String columnIdName) throws SQLException;
    <T> Object insert(T entity) throws SQLException, NoSuchFieldException;
    <T> List<T> findAll(Class<T> entityClass) throws SQLException;
    <T> T update(T entity) throws SQLException;
    void delete(Object entity) throws SQLException;
    <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException;
    <T> List<T> findEmployeesByDepartment(String partOfName) throws SQLException;
    <T> void insertTransaction(List<T>  entities) throws SQLException;
}
