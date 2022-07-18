package com.test.repository;

import java.util.List;

public interface JdbcRepository<T> {
	List<T> findAll();
	T findById(Long id);
	void insert(Object object);
	List<T> findByCondition(String sql);
}
