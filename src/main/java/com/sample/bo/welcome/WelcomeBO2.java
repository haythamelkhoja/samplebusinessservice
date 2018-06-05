package com.sample.bo.welcome;

import org.springframework.stereotype.Service;

import com.sample.dao.customer.Customer;
import com.sample.service.contants.BoNames;

/**
 * 
 * @author N44947
 *
 *         Business object for welcome operations. Test01 feature.
 * 
 */
@Service(BoNames.BO_WELCOME_TEST01)
class WelcomeBO2 extends WelcomeBaseBO {

	@Override
	protected String getNameForMessage(Customer pOBCustomer) throws Exception {
		StringBuilder lOBStringBuilder = new StringBuilder();
		lOBStringBuilder.append(pOBCustomer.getFirstName()).append(" - ").append(pOBCustomer.getLastName());

		return lOBStringBuilder.toString();
	}
}
