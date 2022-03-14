package com.bracamod.geo.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bracamod.geo.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	
	/**
	 * Get all the cities by name
	 * @param name The name of the city
	 * @return
	 */
	@Query("select c from City c where c.name = ?1")
	Optional<List<City>> findByName(String name);
	
}
