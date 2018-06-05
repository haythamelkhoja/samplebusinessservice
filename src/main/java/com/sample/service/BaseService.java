package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

	@Autowired
	private BusinessObjectFactory mOBBusinessObjectFactory = null;

	public BaseService() {
		super();
	}

	protected final BusinessObjectFactory getBoFactory() {
		return mOBBusinessObjectFactory;
	}

}
