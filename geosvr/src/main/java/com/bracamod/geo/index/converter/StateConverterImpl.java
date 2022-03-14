package com.bracamod.geo.index.converter;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bracamod.geo.entity.State;
import com.bracamod.geo.dto.StateDto;

/**
 * 
 * @author daniel
 *
 */
@Service
public class StateConverterImpl implements GenericConverter<State, StateDto> {

	@Autowired
	private CityConverterImpl cityConverter;
	
	@Override
	public StateDto apply(State state) {
		
		StateDto stateDto = new StateDto();
		stateDto.setId(state.getId());
		stateDto.setName(state.getName());
		stateDto.setCities(cityConverter.convert(state.getCities()));
		
		return stateDto;
	}

}
