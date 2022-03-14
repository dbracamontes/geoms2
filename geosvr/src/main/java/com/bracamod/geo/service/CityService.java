package com.bracamod.geo.service;

import java.util.List;
import java.util.Optional;
import com.bracamod.geo.entity.City;

public interface CityService {
	
	Optional<List<City>> findByName(String name);
	
	
}
