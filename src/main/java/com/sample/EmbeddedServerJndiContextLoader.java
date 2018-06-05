package com.sample;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 
 * @author N44947
 *
 *         This class loads data source configuration information only for
 *         embedded Tomcat server (Spring Boot Application). It has no effect
 *         when the application deployed to a server.
 */
@Configuration
@PropertySource({ "classpath:embeddedTomcatJndi.properties" })
public class EmbeddedServerJndiContextLoader {

	@Autowired
	private Environment mOBEnvironment;

	@Bean
	@Lazy
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName(mOBEnvironment.getProperty("jndi.name"));
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName", mOBEnvironment.getProperty("jndi.driverClassName"));
				resource.setProperty("url", mOBEnvironment.getProperty("jndi.url"));
				resource.setProperty("username", mOBEnvironment.getProperty("jndi.username"));
				resource.setProperty("password", mOBEnvironment.getProperty("jndi.password"));

				context.getNamingResources().addResource(resource);
			}
		};
	}

}
