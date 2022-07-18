package com.test.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.test.annotation.Column;

public class ResultsetMapper<T> {
	// Có thể cho ra các field ra Map để đỡ phải duyêt đi lại nhiều lần
	public List<T> mapRow(ResultSet rs, Class<T> tClass) {
		List<T> result = new ArrayList<T>();
		try {
			Field[] fields = tClass.getDeclaredFields();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			while (rs.next()) {
				T object = tClass.getDeclaredConstructor().newInstance();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); ++i) {
					String columnName = resultSetMetaData.getColumnName(i + 1);
					Object columnValue = rs.getObject(i + 1);

					for (Field field : fields) {
						if (field.isAnnotationPresent(Column.class)) {
							Column column = field.getAnnotation(Column.class);
							if (columnValue != null && columnName.equals(column.name())) {
								// set các property vào object để chút add vào List
								BeanUtils.setProperty(object, field.getName(), columnValue);
								break;
							}
						}
					}

					Class<?> parentClass = tClass.getSuperclass();
					while ((parentClass != null) && (parentClass != Object.class)) {
						for (Field field : parentClass.getDeclaredFields()) {
							if (parentClass.isAnnotationPresent(Column.class)) {
								Column column = parentClass.getAnnotation(Column.class);
								if (columnValue != null && columnName.equals(column.name())) {
									BeanUtils.setProperty(object, field.getName(), columnValue);
									break;
								}
							}
						}
						parentClass = parentClass.getSuperclass();
					}
				}
				result.add(object);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return result;
	}
}
