package com.sample.service;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.bo.IBaseBO;
import com.sample.dao.feature.FeatureDAO;
import com.sample.shared.context.BeanApplicationContext;
import com.sample.shared.exception.SharedException;

/**
 * 
 * @author N44947
 *
 *         This factory class invokes the related BO object for the given
 *         feature
 */
@Component
public class BusinessObjectFactory {

	@Autowired
	private BeanApplicationContext mOBApplicationContext;

	@Autowired
	private FeatureDAO mOBFeatureDAO;

	private BusinessObjectFactory() {
		super();
	}

	public String getActiveFeatureName(String pSTServiceName) throws SharedException {
		try {
			return mOBFeatureDAO.findByServiceNameAndActive(pSTServiceName, true).getFeatureName();
		} catch (Exception e) {
			// Cannot find any feature with given parameter. So return default feature's
			// name.
			throw new SharedException(e.getMessage());
		}
	}

	public IBaseBO getBusinessObject(String pSTServiceName) throws SharedException {
		// 1. Get active feature name for the service
		String lSTActiveFeatureName = getActiveFeatureName(pSTServiceName);

		// 2. Get BO object related with the feature
		try {
			return (IBaseBO) mOBApplicationContext.getBean(lSTActiveFeatureName);
		} catch (NoSuchBeanDefinitionException e) {
			// Cannot find any BO bean related with the feature. So return default BO's
			// name.
			throw new SharedException(e.getMessage());
		}
	}
}
