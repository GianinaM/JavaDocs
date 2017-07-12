package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Gianina.Carp on 7/12/2017.
 */
public class EntityUtils {

    private EntityUtils() throws UnsupportedOperationException {}

    public static String getTableName(Class entity) {
        if (!entity.isAnnotationPresent(Table.class)) {
            return entity.getName();
        } else {
            return ((Table)entity.getAnnotation(Table.class)).name();
        }
    }

    public static  ArrayList<ColumnInfo> getColumns(Class entity) {
        int  i = 0;
        ArrayList<ColumnInfo> columns = new ArrayList<ColumnInfo>();
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)|| field.isAnnotationPresent(Id.class)) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnName(field.getName());
                columnInfo.setColumnType(field.getType());
                if (field.isAnnotationPresent(Column.class)) {
                    columnInfo.setDbColumnName(field.getAnnotation(Column.class).name());
                    columnInfo.setId(false);
                } else {
                    columnInfo.setDbColumnName(field.getAnnotation(Id.class).name());
                    columnInfo.setId(true);
                }
                columns.add(columnInfo);
            }
        }
        return columns;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if ((value instanceof BigDecimal) && wantedType.getName().equals("java.lang.Integer")){
            return ((BigDecimal) value).intValue();
        } else if ((value instanceof BigDecimal) && wantedType.getName().equals("java.lang.Long")) {
            return ((BigDecimal) value).longValue();
        } else if ((value instanceof BigDecimal) && wantedType.getName().equals("java.lang.Float")) {
            return ((BigDecimal) value).longValue();
        } else if ((value instanceof BigDecimal) && wantedType.getName().equals("java.lang.Double")) {
            return ((BigDecimal) value).longValue();
        } else return value;
    }

    public static ArrayList<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        ArrayList<Field> fields = new ArrayList<Field>();
        int i = 0;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(annotation) != null) {
                fields.add(field);
            }
        }
        return fields;
    }

    public static Object getSqlValue(Object object) {
        if (object.getClass().getAnnotation(Table.class) != null) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return field.getName();
                }
            }
        }
        return object;
    }
}
