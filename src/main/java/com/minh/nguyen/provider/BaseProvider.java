package com.minh.nguyen.provider;

import com.minh.nguyen.entity.BaseEntity;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base prepared SQL statement
 */
@Component
public class BaseProvider {
    public String updateByPK(final Class<? extends BaseEntity> entity) {
        return new SQL(){{

        }}.toString();
    }
    public String selectByPK(final Class<?> entity){
        return new SQL(){{
            Class<?> C = entity.getClass();
            List<Field> getAllFields = new ArrayList<>();
            while (C != null) {
                Field[] currentField = entity.getDeclaredFields();
                for(int i = 0;i < currentField.length;i++){
                    getAllFields.add(currentField[i]);
                }
                C = C.getSuperclass();
            }
            StringBuilder select = new StringBuilder();
            for(int i = 0;i < getAllFields.size();i++){
                select.append(getAllFields.get(i).getAnnotation(Column.class).name());
                select.append(" AS ");
                select.append(getAllFields.get(i).getName());
                if (i != getAllFields.size() - 1){
                    select.append(",");
                }
            }
            StringBuilder where = new StringBuilder();
            for(int i = 0;i < getAllFields.size();i++){
                if (getAllFields.get(i).isAnnotationPresent(Id.class)){
                    select.append(getAllFields.get(i).getAnnotation(Column.class).name());
                    select.append("=");
                    select.append("#{" + getAllFields.get(i).getName() + "}");
                }
            }
            SELECT(select.toString());
            FROM(entity.getAnnotation(Table.class).name());
            WHERE(where.toString());
            WHERE("deleteFlg='0'");
        }}.toString();
    }
    public String insert(final Class<?> entity){
        return new SQL(){{

        }}.toString();
    }
}
