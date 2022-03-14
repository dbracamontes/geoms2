package com.bracamod.geo.features;

import static org.junit.Assert.assertEquals;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.bracamod.geo.entity.State;
import com.bracamod.geo.test.CucumberStepDefinitions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StateFeature  extends CucumberStepDefinitions{
	
	private ResponseEntity<State> response;
	
	// @When("^the client calls state find by (\\d)$")
	@When("^the client calls state find by$")
	public void callFind() {
		RestTemplate restTemplate = new RestTemplate();
		
		response = restTemplate.getForEntity("http://localhost:8080/geo/states/1", State.class);
	
	}
 
	@Then("^the client receives response status$")
	public void then()  {
		
		assertEquals(200,response.getStatusCode());
	}
 
}
