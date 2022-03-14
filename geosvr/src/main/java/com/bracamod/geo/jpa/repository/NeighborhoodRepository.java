
package com.bracamod.geo.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bracamod.geo.entity.Neighborhood;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood,Long>{
	
	/**
	 * Get all the neigborhoods by name
	 * @param name
	 * @return
	 */
	@Query("select n from Neighborhood n where n.name = ?1 ")
	List<Neighborhood> findNeighborByName(String name);
	
	/**
	 * Get all neighborhoods by zipcode
	 * @param zipCode
	 * @return
	 */
	@Query("select n from Neighborhood n where n.zipCode=:zipCode")
	List<Neighborhood> findNeighborByZipCode(@Param("zipCode") int zipCode);
	
	@Query("select n from Neighborhood n where n.name like ?1 ")
	List<Neighborhood> findNeighborByNameLike(String name);
	
	Page<Neighborhood> findNeighborByNameLike(String name, Pageable pageable);
	
	

}
