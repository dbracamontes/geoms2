package com.bracamod.geo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.bracamod.geo.entity.City;
import com.bracamod.geo.jpa.repository.CityRepository;
import com.bracamod.geo.service.CityService;

@RestController
@RequestMapping(value = "/cities", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CityController {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public List<City> getAll(){	
		return cityRepository.findAll();
	}

	@GetMapping("/{cityId}")
	@ResponseBody
	public ResponseEntity<City> getCityById(@PathVariable Long cityId){
		Optional<City> optional = cityRepository.findById(cityId);
		City city = optional.isPresent() ? optional.get() : null;
		HttpStatus status = optional.isPresent() ?  HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<> (city,status);
	} 
	
	@GetMapping("/name/{name}")
	@ResponseBody
	public ResponseEntity<List<City>> getCityByName(@PathVariable String name){
		Optional<List<City>> optional = cityService.findByName(name);
		HttpStatus status = optional.isPresent() ?  HttpStatus.OK : HttpStatus.NOT_FOUND;
		List<City> cities = optional.isPresent() ? optional.get() : null;
		
		return new ResponseEntity<>(cities,status);
	} 

}
