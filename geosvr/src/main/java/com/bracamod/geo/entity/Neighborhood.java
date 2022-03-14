package com.bracamod.geo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "neighborhood", indexes = { @Index(name="idx_neighborId",columnList="id")}
		)
public class Neighborhood {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="zip_code")
	private int zipCode;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_city")
	private City city;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_settlement_type")
	private UrbanSettlement urbanSettlementType;
	

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public UrbanSettlement getUrbanSettlementType() {
		return urbanSettlementType;
	}

	public void setUrbanSettlementType(UrbanSettlement urbanSettlementType) {
		this.urbanSettlementType = urbanSettlementType;
	}

	@Override
	public String toString() {
		return "Neighborhood [id=" + id + ", name=" + name + ", zipCode=" + zipCode + "]";
	}
	
}
