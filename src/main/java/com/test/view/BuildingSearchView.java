package com.test.view;

import java.util.List;

import com.test.controller.BuildingController;
import com.test.model.input.BuildingSearchInput;
import com.test.model.output.BuildingSearchOutput;

public class BuildingSearchView {
	public static void main(String[] args) {
		BuildingSearchInput buildingSearchInput = new BuildingSearchInput();
		buildingSearchInput.setName("Nam Giao Building Tower");

		BuildingController buildingController = new BuildingController();
		List<BuildingSearchOutput> result = buildingController.findBuilding(buildingSearchInput);

		if(result == null) {
			System.out.println("is Null");
		} else {			
			result.forEach(item -> System.out.println(item.getName()));
		}
	}
}
