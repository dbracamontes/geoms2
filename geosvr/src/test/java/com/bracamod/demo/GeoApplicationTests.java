package com.bracamod.demo;

import java.util.List;
import java.util.Optional;
// import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.bracamod.geo.dto.StateDto;
import com.bracamod.geo.entity.Neighborhood;
import com.bracamod.geo.entity.State;
import com.bracamod.geo.index.converter.StateConverterImpl;
import com.bracamod.geo.jpa.repository.NeighborhoodRepository;
import com.bracamod.geo.jpa.repository.StateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories("com.bracamod.geo.jpa.repository")
@EntityScan("com.bracamod.geo.entity")
public class GeoApplicationTests {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	
	@Autowired
	private StateConverterImpl stateConverter;

	@Test
	public void contextLoads() {
		System.out.println(stateRepository.findAll());
	}

	@Test
	public void testNegihboorhood() {
		try {
			Optional<Neighborhood> neighborhood = neighborhoodRepository.findById(1L);

			System.out.println(neighborhood.get().getCity());
			System.out.println(neighborhood.get().getUrbanSettlementType());
			System.out.println(neighborhood.get());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	@Test
	public void getNeighborByName() {
		try {
			List<Neighborhood> neighborhood = neighborhoodRepository.findNeighborByName("Agua Clara");
			
			System.out.println(neighborhood);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}
	

	@Test
	public void getNeighborByNameLike() {
		try {
			Page<Neighborhood> neighborhood = neighborhoodRepository.findNeighborByNameLike("San An",PageRequest.of(1, 20));

			
			System.out.println(neighborhood);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}
	
	@Test
	public void testStateConverter() {
		Optional<State> state = stateRepository.findById(1L);
		StateDto stateDto = stateConverter.convert(state.get());
		
		System.out.println(stateDto);
		
	}
	
	
	
}
