package com.bracamod.geo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bracamod.geo.entity.State;
import com.bracamod.geo.jpa.repository.StateRepository;

@RestController
@RequestMapping(value = "/states" , produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping
	public List<State> findAllStates(){
		return stateRepository.findAll();
	}
	
	@GetMapping("/{stateId}")
	@ResponseBody
	public ResponseEntity<State> findStateById(@PathVariable Long stateId) {
		Optional<State> optional = stateRepository.findById(stateId);
		State state = optional.isPresent() ? optional.get() : null;
		HttpStatus responseStatus = optional.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(state, responseStatus);
	} 
	
	@DeleteMapping("/{stateId}")
	public void deleteStateById(@PathVariable Long stateId) {
			stateRepository.deleteById(stateId);
	}

}
