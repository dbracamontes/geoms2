package com.bracamod.geo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bracamod.geo.entity.UrbanSettlement;

public interface  UrbanSettlementRepository extends JpaRepository<UrbanSettlement, Long>{

	/**
	 * Get all the UrbanSettlements by name
	 * @param name
	 * @return
	 */
	@Query("select u from UrbanSettlement u where u.name = :name ")
	List<UrbanSettlement> findUrbanSetByName(@Param("name") String name);
}
