package com.bracamod.geo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bracamod.geo.entity.City;
import com.bracamod.geo.jpa.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityRepository cityRepository;

	@Override
	public Optional<List<City>> findByName(String name) {
		return cityRepository.findByName(name);
	}

}
