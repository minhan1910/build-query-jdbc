package com.test.service;

import java.util.List;

import com.test.model.dto.BuildingDTO;
import com.test.model.input.BuildingSearchInput;
import com.test.model.output.BuildingSearchOutput;

public interface BuildingService {
	List<BuildingSearchOutput> findBuiling(BuildingSearchInput buildingSearchInput);
	BuildingDTO insertBuilding(BuildingDTO buildingDTO);
	List<BuildingDTO> findAll();
	BuildingDTO findById(Long id);
}
