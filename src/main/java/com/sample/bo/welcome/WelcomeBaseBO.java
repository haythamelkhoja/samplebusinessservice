package com.sample.bo.welcome;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.bo.BaseBO;
import com.sample.dao.customer.Customer;
import com.sample.dao.customer.CustomerDao;
import com.sample.shared.welcome.WelcomePojo;

abstract class WelcomeBaseBO extends BaseBO implements IWelcomeBO {

	@Autowired
	private CustomerDao mOBCustomerRepository;

	@Autowired
	private Greeting mOBGreeting;

	protected final CustomerDao getCustomerRepository() {
		return mOBCustomerRepository;
	}

	public WelcomeBaseBO() {
		super();
	}

	public WelcomePojo welcome(String pSTName) {

		String lSTMessage = mOBGreeting.getMessage(pSTName);
		WelcomePojo lOBWelcomePojo = new WelcomePojo(lSTMessage, new Date());

		return lOBWelcomePojo;
	}

	public WelcomePojo welcomeId(Integer pINId) {
		Customer lOBCustomer = null;
		String lSTMessage;
		try {
			lOBCustomer = getCustomerRepository().findOne(pINId);
			if (lOBCustomer != null) {
				lSTMessage = getNameForMessage(lOBCustomer);
			} else {
				lSTMessage = String.valueOf(pINId);
			}
			return welcome(lSTMessage);
		} catch (Exception e) {
			if (lOBCustomer == null) {
				lSTMessage = "Customer is null. Not found for id: " + pINId;
			} else {
				lSTMessage = e.getMessage();
			}
			return welcome(lSTMessage);
		}
	}

	protected abstract String getNameForMessage(Customer pOBCustomer) throws Exception;

}
