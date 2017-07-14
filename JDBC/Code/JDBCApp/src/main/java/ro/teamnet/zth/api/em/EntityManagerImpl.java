package ro.teamnet.zth.api.em;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
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
        try (Statement statement  = conn.createStatement()){
            statement.executeQuery(SQL);
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return findById(entity.getClass(), nextId);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws SQLException {

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

        return findInResult(columnInfos, entityClass, SQL);
    }

    @Override
    public <T> T update(T entity) throws SQLException {

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(entity.getClass());

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());

        Long id = findIdFromConditionsInfos(columnInfos, entity);

        //create conditions by Id
        Condition condition = new Condition();
        condition.setColumnName(findColumnsIdFromColumns(columnInfos));
        condition.setValue(id);

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnInfos);
        queryBuilder.addCondition(condition);

        //set condition and type
        queryBuilder.setQueryType(QueryType.UPDATE);
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = DBManager.getConnection().createStatement()){
            int resultSet = statement.executeUpdate(SQL);
            if (resultSet > 0) {
                return entity;
            }
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        }
        return null;
    }

    @Override
    public void delete(Object entity) throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(entity.getClass());

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());

        Long id = findIdFromConditionsInfos(columnInfos, entity);

        //create conditions by Id
        Condition condition = new Condition();
        condition.setColumnName(findColumnsIdFromColumns(columnInfos));
        condition.setValue(id);

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);

        //set condition and type
        queryBuilder.setQueryType(QueryType.DELETE);
        String SQL = queryBuilder.createQuery();

        //create resultSet
        try (Statement statement  = DBManager.getConnection().createStatement()){
            int result = statement.executeUpdate(SQL);
            if (result < 0) throw new NotFound();
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException {

        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(entityClass);

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);

        for (ColumnInfo columnInfo : columnInfos){
            try {
                Field field = entityClass.getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entityClass.newInstance()));
            } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        //create conditions by Id
        for (Map.Entry<String, Object> param : params.entrySet()) {
            Condition condition = new Condition();
            condition.setColumnName(param.getKey());
            condition.setValue(param.getValue());
            queryBuilder.addCondition(condition);
        }

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnInfos);

        //set condition and type
        queryBuilder.setQueryType(QueryType.SELECT);
        String SQL = queryBuilder.createQuery();

        return findInResult(columnInfos, entityClass, SQL);
    }


    @Override
    public <T> List<T> findEmployeesByDepartment(String partOfName) throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        // setTableName
        String tableName = EntityUtils.getTableName(Employee.class);
        String joinTableName = EntityUtils.getTableName(Department.class);

        //add Columns to query
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(Employee.class);

        //create conditions by Id
        Condition joinCondition = new Condition();
        joinCondition.setValue("department_id");
        joinCondition.setColumnName("department_id");

        Condition condition = new Condition();
        condition.setColumnName("department_name");
        condition.setValue(partOfName);

        //create query
        queryBuilder.setTableName(tableName);
        queryBuilder.setJoinTableName(joinTableName);
        queryBuilder.addQueryColumns(columnInfos);
        queryBuilder.addCondition(condition);
        queryBuilder.addJoinCondition(joinCondition);

        //set condition and type
        queryBuilder.setQueryType(QueryType.SELECT);
        String SQL = queryBuilder.createQuery();

        System.out.println(SQL);

        return (List<T>) findInResult(columnInfos, Employee.class, SQL);
    }

    @Override
    public <T> void insertTransaction(List<T> entities) throws SQLException {
        Connection conn = DBManager.getConnection();
        conn.setAutoCommit(false);

        for (T entity : entities) {
            QueryBuilder queryBuilder = new QueryBuilder();
            // setTableName
            String tableName = EntityUtils.getTableName(entity.getClass());

            //add Columns to query
            List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
            long nextId = 0;

            for (ColumnInfo columnInfo : columnInfos) {
//                if (columnInfo.isId()) {
//                    nextId = this.getNextIdVal(tableName, columnInfo.getDbColumnName());
//                    columnInfo.setValue(nextId);
//                } else {
                    try {
                        Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                        field.setAccessible(true);
                        columnInfo.setValue(field.get(entity));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
//                }
            }

            //create query
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columnInfos);
            //set condition and type
            queryBuilder.setQueryType(QueryType.INSERT);
            String SQL = queryBuilder.createQuery();

            //create resultSet
            try (Statement statement = conn.createStatement()) {
                statement.executeQuery(SQL);
            } catch (SQLException e) {
                System.out.println("Error: checkConnection faild");
            }
        }
        conn.setAutoCommit(true);
        conn.close();
//        return findById(entity.getClass(), nextId);
    }

    private <T> List<T> findInResult(List<ColumnInfo> columnInfos, Class<T> entityClass, String SQL){
        List<T> result = new ArrayList<>();
        try (Statement statement  = DBManager.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                T myInstance = entityClass.newInstance();
                for (ColumnInfo columnInfo : columnInfos){
                    Field field = myInstance.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.setAccessible(true);
                    field.set(myInstance,
                            EntityUtils.castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()),
                                    columnInfo.getColumnType()));
                }
                result.add(myInstance);
            }
        }
        catch (SQLException e) {
            System.out.println("Error: checkConnection faild");
        } catch (IllegalAccessException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private long findIdFromConditionsInfos(List<ColumnInfo> columnInfos, Object entity){
        long id = 0L;
        for (ColumnInfo columnInfo : columnInfos){
            try {
                Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                columnInfo.setValue(field.get(entity));
                if(columnInfo.isId()) {
                    id = (Long) field.get(entity);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private String findColumnsIdFromColumns(List<ColumnInfo> columnInfos) {
        for (ColumnInfo columnInfo : columnInfos) {
            if (columnInfo.isId()) {
                return columnInfo.getDbColumnName();
            }
        }
        return "";
    }
}
