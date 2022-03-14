package com.bracamod.geo.dto;

public class NeighborhoodDto {
	private Long id;
	private String name;
	private int zipCode;
	private String type;
	
	/**
	 * 
	 */
	public NeighborhoodDto() {
		super();
	}

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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Neighborhood [id=" + id + ", name=" + name + ", zipCode=" + zipCode + ", type=" + type + "]";
	}

}
