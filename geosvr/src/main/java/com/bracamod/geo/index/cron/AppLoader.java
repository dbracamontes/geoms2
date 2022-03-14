package com.bracamod.geo.index.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bracamod.geo.service.IndexService;

/**
 * 
 * @author daniel
 *
 */
@Component
public class AppLoader implements CommandLineRunner {

	@Autowired
	IndexService indexService;

	@Override
	public void run(String... args) throws Exception {
		indexService.builkIndex();
	}

}
