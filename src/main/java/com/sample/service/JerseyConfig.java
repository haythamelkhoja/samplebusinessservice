package com.sample.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	private static final String JERSEY_PACKAGES = "com";

	public JerseyConfig() {
		packages(true, JERSEY_PACKAGES);
	}
}
