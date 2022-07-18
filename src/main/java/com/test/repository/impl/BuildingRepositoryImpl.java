package com.test.repository.impl;

import java.util.List;

import com.test.constant.SystemConstant;
import com.test.repository.BuildingRepository;
import com.test.repository.entity.BuildingEntity;

public class BuildingRepositoryImpl extends SimpleJdbcRepository<BuildingEntity> implements BuildingRepository {

	public BuildingRepositoryImpl() {
		//https://stackoverflow.com/questions/10589767/class-cannot-be-cast-to-java-lang-reflect-parameterizedtype
		// For safety -> can get entity type like this for performance and safety
//		super(BuildingEntity.class);
	}

	public List<BuildingEntity> findBuilding(String name, String street, String ward, String district,
			Integer floorArea) {

		/*
		 * Build ra câu SQL để find -> SELECT * FROM tableName name like %...% and ...
		 * 
		 * Cho cái annotation để mark all entity and use reflection technique to handle
		 * this
		 */

		StringBuilder sql = new StringBuilder("SELECT * FROM building " + SystemConstant.WHERE_ONE_EQUAL_ONE);

		if (name != null && name != "")
			sql.append(" and name like '%" + name + "%'");
		if (street != null && street != "")
			sql.append(" and street like '%" + street + "%'");
		if (ward != null && ward != "")
			sql.append(" and ward like '%" + ward + "%'");
		if (district != null && district != "")
			sql.append(" and district like '%" + district + "%'");
		if (floorArea != null)
			sql.append(" and floorarea = " + floorArea);
		return super.findByCondition(sql.toString());
	}

	public BuildingEntity insertBuilding(BuildingEntity buildingEntity, Integer[] rentAreas) {
		return null;
	}

	public BuildingEntity findById(Long id) {
		return super.findById(id);
	}

}
