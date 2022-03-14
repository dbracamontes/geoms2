package com.bracamod.geo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bracamod.geo.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long>{
	
	/**
	 * Get all the states by name
	 * @param name
	 * @return
	 */
	@Query("select s from State s where s.name = :name")
	List<State> findStateByName(@Param("name") String name);

}
