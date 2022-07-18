package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.test.model.dto.BuildingDTO;
import com.test.model.input.BuildingSearchInput;
import com.test.model.output.BuildingSearchOutput;
import com.test.repository.BuildingRepository;
import com.test.repository.entity.BuildingEntity;
import com.test.repository.impl.BuildingRepositoryImpl;
import com.test.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {

	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingSearchOutput> findBuiling(BuildingSearchInput buildingSearchInput) {
		List<BuildingSearchOutput> results = new ArrayList<BuildingSearchOutput>();

		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchInput.getName(),
				buildingSearchInput.getStreet(), buildingSearchInput.getWard(), buildingSearchInput.getDistrict(),
				buildingSearchInput.getFloorArea());

		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingSearchOutput buildingSearchOutput = new BuildingSearchOutput();
			buildingSearchOutput.setId(buildingEntity.getId());
			String address = buildingEntity.getStreet() + " " + buildingEntity.getWard();
			buildingSearchOutput.setAddress(address);
			buildingSearchOutput.setName(buildingEntity.getName());
			results.add(buildingSearchOutput);
		}

		return results;
	}

	@Override
	public BuildingDTO insertBuilding(BuildingDTO buildingDTO) {
		return null;
	}

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();

		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingDTO newBuildingDTO = new BuildingDTO();
			newBuildingDTO.setId(buildingEntity.getId());
			newBuildingDTO.setName(buildingEntity.getName());
			newBuildingDTO.setStreet(buildingEntity.getStreet());
			newBuildingDTO.setWard(buildingEntity.getWard());
			buildingDTOs.add(newBuildingDTO);
		}

		return buildingDTOs;
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = this.buildingRepository.findById(id);
		
		BuildingDTO newBuildingDTO = new BuildingDTO();
		newBuildingDTO.setId(buildingEntity.getId());
		newBuildingDTO.setName(buildingEntity.getName());
		newBuildingDTO.setStreet(buildingEntity.getStreet());
		newBuildingDTO.setWard(buildingEntity.getWard());

		return newBuildingDTO;
	}

}
