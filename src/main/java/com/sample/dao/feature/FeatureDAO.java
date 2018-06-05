package com.sample.dao.feature;

import org.springframework.data.repository.CrudRepository;

public interface FeatureDAO extends CrudRepository<Feature, String> {

	public Feature findByServiceNameAndActive(String serviceName, Boolean active);

}
