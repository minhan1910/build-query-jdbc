package com.test.repository;

import java.util.List;

import com.test.repository.entity.BuildingEntity;

public interface BuildingRepository extends JdbcRepository<BuildingEntity> {
	List<BuildingEntity> findBuilding(String name, String street, String ward, String district, Integer floorArea);
	BuildingEntity insertBuilding(BuildingEntity buildingEntity, Integer[] rentAreas);
	BuildingEntity findById(Long id);
}
