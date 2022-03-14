package com.bracamod.geo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bracamod.geo.entity.State;

@RestController
@RequestMapping(value = "/fake1", produces=MediaType.APPLICATION_JSON_VALUE)
public class FakeService1 {
	
	@GetMapping("/fakeMethod")
	@ResponseBody
	public ResponseEntity<State> fakeMethod() throws Exception {
		throw new Exception("Fake service 1 exception ");
	} 
	

}
