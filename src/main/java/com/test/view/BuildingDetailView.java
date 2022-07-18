package com.test.view;

import com.test.controller.BuildingController;
import com.test.model.dto.BuildingDTO;

public class BuildingDetailView {
	public static void main(String[] args) {
		BuildingController buildingController = new BuildingController();
		BuildingDTO result = buildingController.findById(1L);
		System.out.println(result.getName());
	}
}
