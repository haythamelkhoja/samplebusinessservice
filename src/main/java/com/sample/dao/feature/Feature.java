package com.sample.dao.feature;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Feature {

	@Id
	@NotNull
	private String serviceName;

	@NotNull
	private String featureName;

	@NotNull
	private Boolean active;

	public Feature() {
		super();
	}

	public Feature(String serviceName) {
		super();
		this.serviceName = serviceName;
		this.active = Boolean.TRUE;
	}

	public Feature(String serviceName, Boolean active) {
		super();
		this.serviceName = serviceName;
		this.active = active;
	}

	public Feature(String serviceName, String featureName, Boolean active) {
		super();
		this.serviceName = serviceName;
		this.featureName = featureName;
		this.active = active;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
