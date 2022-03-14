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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "city", indexes = { @Index(name = "idx_cityId", columnList = "id") })
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_state")
	private State state;
	
	@JsonIgnore
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<Neighborhood> neighborhoods;

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
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	

	/**
	 * @return the neighborhoods
	 */
	public List<Neighborhood> getNeighborhoods() {
		return neighborhoods;
	}

	/**
	 * @param neighborhoods the neighborhoods to set
	 */
	public void setNeighborhoods(List<Neighborhood> neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + "]";
	}

}
