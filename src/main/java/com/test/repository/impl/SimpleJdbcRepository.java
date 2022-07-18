package com.test.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.annotation.Entity;
import com.test.annotation.Table;
import com.test.mapper.ResultsetMapper;
import com.test.repository.JdbcRepository;
import com.test.utils.ConnectionUtils;

public class SimpleJdbcRepository<T> implements JdbcRepository<T> {

	private Class<T> tClass;
	private ResultsetMapper<T> resultsetMapper;
	
	@SuppressWarnings("unchecked")
	public SimpleJdbcRepository() {
		 resultsetMapper = new ResultsetMapper<T>();
		// This methodology is error-prone
//		tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null) {
			if ((genericSuperClass instanceof ParameterizedType)) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
			}
		}
		tClass = (Class<T>) parametrizedType.getActualTypeArguments()[0];
	}

	public List<T> findAll() {
		List<T> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String tableName = null;

			// GET TABLE NAME
			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}

			String sql = "SELECT * FROM " + tableName + "";
			rs = stmt.executeQuery(sql);
			result = this.resultsetMapper.mapRow(rs, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public T findById(Long id) {
		List<T> result = new ArrayList<T>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			String tableName = null;
			if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "SELECT * FROM " + tableName + " where id = " + id;
			rs = stmt.executeQuery(sql);
			result = this.resultsetMapper.mapRow(rs, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(conn != null)
						conn.close();
					if(stmt != null)
						stmt.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return result.size() > 0 ? result.get(0) : null;
	}

	public void insert(Object object) {
		
	}

	public List<T> findByCondition(String sql) {
		List<T> result = new ArrayList<T>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			result = this.resultsetMapper.mapRow(rs, tClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
