package com.test.controller;

import java.util.List;

import com.test.model.dto.BuildingDTO;
import com.test.model.input.BuildingSearchInput;
import com.test.model.output.BuildingSearchOutput;
import com.test.service.BuildingService;
import com.test.service.impl.BuildingServiceImpl;

public class BuildingController {
	private BuildingService buildingService = new BuildingServiceImpl();
	
	public List<BuildingSearchOutput> findBuilding(BuildingSearchInput buildingSearchInput) {
		return buildingService.findBuiling(buildingSearchInput);
	}
	
	public BuildingDTO findById(Long id) {
		return buildingService.findById(id);
	}
	
 	public List<BuildingDTO> findAll() {
		return buildingService.findAll();
	}
}
