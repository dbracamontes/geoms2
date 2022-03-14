package com.bracamod.geo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	private static Random random;
	
	static {
		random = new Random();
	}
	 

	@Override
	// @HystrixCommand
	public Optional<List<City>> findByName(String name) {
		randomlyRunLong();
		return cityRepository.findByName(name);
	}

	private void randomlyRunLong() {
		int randomNum = random.nextInt((3 - 1) + 1) + 1;

		if (randomNum == 3) {
			
			try {
				Thread.sleep(11000);
			} catch (InterruptedException ex) {
				log.error(ex.getMessage());
			}
		}
	}

}
