package com.bracamod.geo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import com.bracamod.geo.dto.StateDto;
import com.bracamod.geo.entity.State;
import com.bracamod.geo.index.converter.StateConverterImpl;
import com.bracamod.geo.jpa.repository.StateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author daniel
 *
 */
@Service
public class IndexService {

	private static final String INDEX_NAME = "geo_index";
	private static final String STATE_INDEX_TYPE = "state_type";
	private static final int INDEX_COMMIT_SIZE = 0;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ElasticsearchTemplate elasticSearchTemplate;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private StateConverterImpl stateConverterImpl;

	public void builkIndex() {
		if (elasticSearchTemplate.indexExists(INDEX_NAME)) {
			elasticSearchTemplate.deleteIndex(INDEX_NAME);	
		}
		
		elasticSearchTemplate.createIndex(INDEX_NAME);

		List<IndexQuery> queries = new ArrayList<>();
		List<State> states = stateRepository.findAll();

		for (State state : states) {
			StateDto stateDto = stateConverterImpl.convert(state);
			ObjectMapper obj = new ObjectMapper();
			String stateJson;
			try {
				stateJson = obj.writeValueAsString(stateDto);
				IndexQuery indexQuery = new IndexQuery();
				indexQuery.setId(state.getId().toString());
				indexQuery.setIndexName(INDEX_NAME);
				indexQuery.setType(STATE_INDEX_TYPE);
				indexQuery.setSource(stateJson);
				queries.add(indexQuery);

				if (queries.size() == INDEX_COMMIT_SIZE) {
					elasticSearchTemplate.bulkIndex(queries);
					queries.clear();
				}

			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
			}
		}
		
		if (!queries.isEmpty())
			elasticSearchTemplate.bulkIndex(queries);
		
		elasticSearchTemplate.refresh(INDEX_NAME);
		log.info("bulkIndex completed.");
	}

}
