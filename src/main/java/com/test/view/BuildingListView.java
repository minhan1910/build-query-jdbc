package com.test.view;

import java.util.List;

import com.test.controller.BuildingController;
import com.test.model.dto.BuildingDTO;

public class BuildingListView {
	public static void main(String[] args) {
		BuildingController buildingController = new BuildingController();
		List<BuildingDTO> buildingDTOs = buildingController.findAll();
		buildingDTOs.forEach(i -> System.out.println(i.getName()));
	}
}
