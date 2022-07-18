package com.test.model.dto;

public class BuildingDTO {
	private Long id;
	private String name;
	private String ward;
	private String district;
	private String street;
	private Integer floorArea;
	private String[] types = new String[] {};
	private Integer[] rentAreas = new Integer[100];

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Integer[] getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(Integer[] rentAreas) {
		this.rentAreas = rentAreas;
	}

}