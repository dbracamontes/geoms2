package com.bracamod.geo.index.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bracamod.geo.dto.CityDto;
import com.bracamod.geo.entity.City;

@Service
public class CityConverterImpl implements GenericConverter<City, CityDto>{

	@Autowired
	private NeighborhoodConverterImpl neighborhoodConverter;
	
	@Override
	public CityDto apply(City city) {
		CityDto cityDto = new CityDto();
		cityDto.setId(city.getId());
		cityDto.setName(city.getName());	
		cityDto.setNeigborhoods(neighborhoodConverter.convert(city.getNeighborhoods()));
		return cityDto;
	}


}
