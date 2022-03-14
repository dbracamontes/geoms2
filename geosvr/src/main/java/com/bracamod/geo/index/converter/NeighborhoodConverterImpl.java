package com.bracamod.geo.index.converter;

import org.springframework.stereotype.Service;
import com.bracamod.geo.dto.NeighborhoodDto;
import com.bracamod.geo.entity.Neighborhood;

/**
 * 
 * @author daniel
 *
 */
@Service
public class NeighborhoodConverterImpl implements GenericConverter<Neighborhood, NeighborhoodDto>{

	@Override
	public NeighborhoodDto apply(Neighborhood neighborhood) {
		NeighborhoodDto neighborhoodDto = new NeighborhoodDto();
		neighborhoodDto.setId(neighborhood.getId());
		neighborhoodDto.setName(neighborhood.getName());
		neighborhoodDto.setZipCode(neighborhood.getZipCode());
		neighborhoodDto.setType(neighborhood.getUrbanSettlementType().getName());
		
		return neighborhoodDto;
	}

}
