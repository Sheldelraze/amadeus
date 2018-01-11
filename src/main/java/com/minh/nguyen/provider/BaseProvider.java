package com.minh.nguyen.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base prepared SQL statement
 */
@Component
public class BaseProvider {

    /**
     * FORMAT_STRING_SQL_3
     */
    private static final String FORMAT_STRING_SQL_3 = "#{";

    /**
     * STRING_COMMA
     */
    private static final String STRING_COMMA = ",";

    /**
     * STRING_AS
     */
    private static final String STRING_AS = " AS ";

    /**
     * FORMAT_STRING_SQL_2
     */
    private static final String FORMAT_STRING_SQL_2 = "}";

    /**
     * FORMAT_STRING_SQL_1
     */
    private static final String FORMAT_STRING_SQL_1 = " = #{";
    /**
     * SQL_COUNT_ALL_RECODE
     */
    private static final String SQL_COUNT_ALL_RECODE = "count(*)";

    /**
     * select record by Id
     * @param entity Object
     * @return String
     */
    public String selectById(Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        return new SQL() {
            {
                String selectClause = "";
                // SELECT("*");
                FROM(tableName);

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genarateSelectedField(String selectClause,
                                                 Field field) {
                if (field.isAnnotationPresent(Id.class)) {
                    WHERE(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
    }

    /**
     * select all
     * @param classType Class<?>
     * @return String
     */
    public String selectAll(Class<?> classType) {

        final Class<?> table = classType;
        final String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                String selectClause = "";

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);

                }

                for (Field field : table.getDeclaredFields()) {
                    selectClause = genarateSelectedField(selectClause, field);
                }
                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
                FROM(tableName);

            }

            private String genarateSelectedField(String selectClause,
                                                 Field field) {
                if (field.isAnnotationPresent(Column.class)) {
                    return selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by primary key
     * @param entity Object
     * @return String
     */
    public String existId(Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();
        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * Check exist of recode by example record
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String existWithExample(final Object entity) throws Exception {

        final Class<?> table = entity.getClass();

        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class)
                            || field.isAnnotationPresent(Id.class)) {
                        if (field.get(entity) != null) {
                            if (field.isAnnotationPresent(Column.class)) {
                                WHERE(field.getAnnotation(Column.class).name()
                                        + FORMAT_STRING_SQL_1 + field.getName()
                                        + FORMAT_STRING_SQL_2);
                            } else {
                                WHERE(field.getAnnotation(Column.class)
                                        .name() + FORMAT_STRING_SQL_1
                                        + field.getName()
                                        + FORMAT_STRING_SQL_2);
                            }
                        }

                    }

                }
            }
        }.toString();
        return sql;
    }

    /**
     * select all record with example record
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String selectWithExample(final  Object entity) throws Exception {

        final Class<?> table = entity.getClass();

        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                String selectClause = "";

                // SELECT("*");
                FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity,
                            selectClause, field);
                }

                for (Field field : entity.getClass().getSuperclass()
                        .getDeclaredFields()) {
                    selectClause = genSqlForSelectWithExample(entity,
                            selectClause, field);
                }

                selectClause = selectClause.substring(0,
                        selectClause.length() - 1);
                SELECT(selectClause);
            }

            private String genSqlForSelectWithExample(Object entity,
                                                      String selectClause,
                                                      Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)
                        || field.isAnnotationPresent(Id.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);

                        } else {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);

                        }
                    }
                }

                if (field.isAnnotationPresent(Column.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                } else if (field.isAnnotationPresent(Id.class)) {
                    selectClause = selectClause
                            + field.getAnnotation(Column.class).name()
                            + STRING_AS + field.getName() + STRING_COMMA;
                }
                return selectClause;
            }
        }.toString();
        return sql;
    }

    /**
     * delete by primary key
     * @param entity Object
     * @return String
     */
    public String deleteById(Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    /**
     * delete with Example record
     * @param entity Object
     * @return String
     * @throws Exception Exception
     */
    public String deleteWithExample(final Object entity) throws Exception {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                DELETE_FROM(tableName);
                for (Field field : entity.getClass().getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }

                for (Field field : entity.getClass().getSuperclass()
                        .getDeclaredFields()) {
                    genSqlForDeleteWithExample(entity, field);
                }
            }

            private void genSqlForDeleteWithExample(Object entity,
                                                    Field field) throws IllegalAccessException {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Column.class)
                        || field.isAnnotationPresent(Id.class)) {
                    if (field.get(entity) != null) {
                        if (field.isAnnotationPresent(Column.class)) {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                        } else {
                            WHERE(field.getAnnotation(Column.class).name()
                                    + FORMAT_STRING_SQL_1 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                        }
                    }

                }
            }
        }.toString();
        return sql;
    }

    /**
     * insert
     * @param entity Object
     * @return String
     */
    public String insert(final Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                INSERT_INTO(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForInsert(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForInsert(field);
                }
            }

            private void genSqlForInsert(Field field) {
                if (field.isAnnotationPresent(Column.class)) {
                    VALUES(field.getAnnotation(Column.class).name(),
                            FORMAT_STRING_SQL_3 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Id.class)) {
                    VALUES(field.getAnnotation(Column.class).name(),
                            FORMAT_STRING_SQL_3 + field.getName()
                                    + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * update by Id
     * @param entity Object
     * @return String
     */
    public String updateById(final Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                UPDATE(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForUpdate(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForUpdate(field);
                }
            }

            private void genSqlForUpdate(Field field) {
                if (field.isAnnotationPresent(Id.class)) {
                    WHERE(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                } else if (field.isAnnotationPresent(Column.class)) {
                    SET(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    /**
     * checkExclusive
     * @param entity Object
     * @return String
     */
    public String checkExclusive(Object entity) {
        final Class<?> table = entity.getClass();
        final String tableName = table.getAnnotation(Table.class).name();

        String sql = new SQL() {
            {
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                for (Field field : table.getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }

                for (Field field : table.getSuperclass().getDeclaredFields()) {
                    genSqlForCheckExclusive(field);
                }
            }

            private void genSqlForCheckExclusive(Field field) {
                if (field.isAnnotationPresent(Id.class)) {
                    WHERE(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                }

                if (field.isAnnotationPresent(Column.class) && "UPDATE_TIME"
                        .equals(field.getAnnotation(Column.class).name())) {
                    WHERE(field.getAnnotation(Column.class).name()
                            + FORMAT_STRING_SQL_1 + field.getName()
                            + FORMAT_STRING_SQL_2);
                }
            }
        }.toString();
        return sql;
    }

    public String getSelectItem(final String tableNm, final String colNm, final String colVal) {
        String sql = new SQL() {
            {
                SELECT(colNm + " AS code", colVal + " AS name");
                FROM(tableNm);
            }
        }.toString();
        return sql;
    }
}
