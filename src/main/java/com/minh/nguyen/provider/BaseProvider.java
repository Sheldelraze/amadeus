package com.minh.nguyen.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base prepared SQL statement
 */
@Component
public class BaseProvider {

    private static final String FORMAT_STRING_SQL_3 = "#{";
    private static final String STRING_COMMA = ",";
    private static final String STRING_AS = " AS ";
    private static final String FORMAT_STRING_SQL_2 = "}";
    private static final String FORMAT_STRING_SQL_1 = " = #{";
    private static final String SQL_COUNT_ALL_RECODE = "count(*)";

    public String selectByPrimaryKey(Object entity) {
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

    public String existPrimaryKey(Object entity) {
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
                if (field.isAnnotationPresent(Column.class)) {
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

    public String deleteByPrimaryKey(Object entity) {
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
                        }
                    }

                }
            }
        }.toString();
        return sql;
    }

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
                }
            }
        }.toString();
        return sql;
    }

    public String updateByPrimaryKey(final Object entity) {
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


    public String updateByPKExceptFields(final Object entity, final List<String> exclusive) {
        String sql = new SQL() {
            {
                Class<?> table = entity.getClass();
                String tableName = table.getAnnotation(Table.class).name();
                UPDATE(tableName);
                while(table != null) {
                    for (Field field : table.getDeclaredFields()) {
                        genSqlForUpdate(field);
                    }
                    table = table.getSuperclass();
                }
            }

            private void genSqlForUpdate(Field field) {
                if (field.isAnnotationPresent(Id.class)) {
                        WHERE(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);

                } else if (field.isAnnotationPresent(Column.class)) {
                    if (!exclusive.contains(field.getName())) {
                        SET(field.getAnnotation(Column.class).name()
                                + FORMAT_STRING_SQL_1 + field.getName()
                                + FORMAT_STRING_SQL_2);
                    }
                }
            }
        }.toString();
        return sql;
    }

    public String checkExclusive(final Object entity) {

        String sql = new SQL() {
            {
                Class<?> table = entity.getClass();
                String tableName = table.getAnnotation(Table.class).name();
                SELECT(SQL_COUNT_ALL_RECODE);
                FROM(tableName);
                while(null != table) {
                    for (Field field : table.getDeclaredFields()) {
                        genSqlForCheckExclusive(field);
                    }
                    table = table.getSuperclass();
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
