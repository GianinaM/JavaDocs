package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

/**
 * Created by Gianina.Carp on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager{
    @Override
    public <T> T findById(Class<T> entityClass, Long id) throws SQLException {
        Connection conn = DBManager.getConnection();

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
       queryBuilder.setTableName(EntityUtils.getTableName(entityClass));
        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);
       queryBuilder.addQueryColumns(columnInfos);
        //get Fields by Annotations
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        //create conditions by Id
        Condition condition = new Condition();
        condition.setColumnName(findColumnsIdFromColumns(columnInfos));
        condition.setValue(id);

        //set condition and type
        queryBuilder.addCondition(condition);
        queryBuilder.setQueryType(QueryType.SELECT);

        //create query
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                T myInstance = entityClass.newInstance();
                for (ColumnInfo columnInfo : columnInfos){
                    Field field = myInstance.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    field.set(myInstance,
                            EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()),
                                    columnInfo.getColumnType()));
                }
                return myInstance;
            }
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getNextIdVal(String tableName, String columnIdName) throws SQLException {
        Connection conn = DBManager.getConnection();

        //create query
        String SQL = String.format("SELECT max(%s) FROM %s", columnIdName, tableName);
        long max = Long.MIN_VALUE;

        //create resultSet
        try (Statement statement  = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            if(resultSet.next())
                max = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return max + 1;
    }

    @Override
    public <T> Object insert(T entity) throws SQLException {
        Connection conn = DBManager.getConnection();

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(entity.getClass());

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
        long nextId = 0;

        for (ColumnInfo columnInfo : columnInfos){
            if(columnInfo.isId()){
                nextId = this.getNextIdVal(tableName, columnInfo.getDbColumnName());
                columnInfo.setValue(nextId);
            } else {
                try {
                    Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(field.get(entity));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnInfos);
        //set condition and type
        queryBuilder.setQueryType(QueryType.INSERT);
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = DBManager.getConnection().createStatement()){
            statement.executeQuery(SQL);
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return findById(entity.getClass(), nextId);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws SQLException {
        Connection conn = DBManager.getConnection();

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        queryBuilder.setTableName(EntityUtils.getTableName(entityClass));
        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);
        queryBuilder.addQueryColumns(columnInfos);

        //set condition and type
        queryBuilder.setQueryType(QueryType.SELECT);

        //create query
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            ArrayList<T> results = new ArrayList<T>();
            while (resultSet.next()) {
                T myInstance = entityClass.newInstance();
                for (ColumnInfo columnInfo : columnInfos){
                    Field field = myInstance.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    field.set(myInstance,
                            EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()),
                                    columnInfo.getColumnType()));
                }
                results.add(myInstance);
            }
            return results;
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T update(T entity) throws SQLException {
        Connection conn = DBManager.getConnection();

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(entity.getClass());

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());

        Long id = 0L;

        for (ColumnInfo columnInfo : columnInfos){
                try {
                    Field field = entity.getClass().getDeclaredField(columnInfo.getDbColumnName());
                    field.setAccessible(true);
                    columnInfo.setValue(field.get(entity));
                    if(columnInfo.isId()) {
                        id = (Long) field.get(entity);
                        System.out.println(id);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                }
        }

        //create conditions by Id
        Condition condition = new Condition();
        condition.setColumnName(findColumnsIdFromColumns(columnInfos));
        condition.setValue(id);

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnInfos);
        //set condition and type
        queryBuilder.setQueryType(QueryType.UPDATE);
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = DBManager.getConnection().createStatement()){
            statement.executeUpdate(SQL);
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return entity;
    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {
        return null;
    }

    public String findColumnsIdFromColumns(List<ColumnInfo> columnInfos) {
        for (ColumnInfo columnInfo : columnInfos) {
            if (columnInfo.isId()) {
                return columnInfo.getDbColumnName();
            }
        }
        return "";
    }
}
