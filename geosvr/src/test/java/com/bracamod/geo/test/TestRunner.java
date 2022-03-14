package com.bracamod.geo.test;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty"},
		features = { "src/test/resources/features" }
		)
public class TestRunner {
 
 
}