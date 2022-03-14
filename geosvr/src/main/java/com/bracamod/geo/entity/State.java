package com.bracamod.geo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.WhereJoinTable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "state", indexes = { @Index(name = "idx_stateId", columnList = "id") })
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "state" ,fetch = FetchType.LAZY)
	private List<City> cities;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "image",
	    joinColumns = {@JoinColumn(name = "ref_id")}
	)
	@WhereJoinTable(clause = "ref_table = 'state' ")
	private List<Image> images;

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

	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name +", images = " + images + "]";
	}

}
