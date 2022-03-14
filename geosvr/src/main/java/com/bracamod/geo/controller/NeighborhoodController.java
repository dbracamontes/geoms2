package com.bracamod.geo.controller;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bracamod.geo.entity.Neighborhood;
import com.bracamod.geo.jpa.repository.NeighborhoodRepository;

@RestController
@RequestMapping(value = "/neighboors", produces=MediaType.APPLICATION_JSON_VALUE)
public class NeighborhoodController {
	
	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	private List<Neighborhood> neigborhoods; 

	@GetMapping
	public List<Neighborhood> getAll(){	
		return neighborhoodRepository.findAll();
	}
	
	@GetMapping("/{neigborhoodId}")
	@ResponseBody
	public ResponseEntity<Neighborhood> findById(@PathVariable Long neigborhoodId){
		Optional<Neighborhood> optional = neighborhoodRepository.findById(neigborhoodId);
		
		Neighborhood neighborhood = optional.isPresent() ? optional.get() : null;
		HttpStatus httpStatus = optional.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		
		return new ResponseEntity<>(neighborhood,httpStatus);		
	}
	
	@GetMapping("/zipCode/{zipCode}")
	@ResponseBody
	public ResponseEntity<List <Neighborhood>> findAllByZipCode(@PathVariable Long zipCode){
		if(neigborhoods == null) {
			neigborhoods = getAll();
			System.out.print("Inisializing getAll neighboors");
		}
		
		Instant start = Instant.now();
		List<Neighborhood> neigborhoodsByZipCode = 
		neigborhoods
			.stream()
			.filter(neighboor -> neighboor.getZipCode() == zipCode)
			.collect(Collectors.toList());
		
		Optional<List<Neighborhood>> optional = Optional.of(neigborhoodsByZipCode);
		HttpStatus httpStatus = optional.isPresent() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("Duration findAllByZipCode " + timeElapsed);
		return new ResponseEntity<>(neigborhoodsByZipCode,httpStatus);		
	}
	
	@GetMapping("/zipCodeDb/{zipCode}")
	@ResponseBody
	public ResponseEntity<List <Neighborhood>> findAllByZipCodeDb(@PathVariable Integer zipCode) {
		Instant start = Instant.now();
		List<Neighborhood> neighboors = neighborhoodRepository.findNeighborByZipCode(zipCode);
		
		HttpStatus httpStatus =  neighboors.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;
		
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("Duration findAllByZipCodeDbZA " + timeElapsed);
		
		Method method[] = null;
		try {
			method = Class.forName("Neighborhood").getMethods();
		} catch (SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		neighboors.stream().sorted(Comparator.comparing(Neighborhood::getName));
			
		return new ResponseEntity<>(neighboors,httpStatus);
	}
	
}
