package com.sample.service.inttest.welcome;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.SampleBusinessServiceApplication;
import com.sample.dao.customer.Customer;
import com.sample.dao.customer.CustomerDao;
import com.sample.dao.feature.Feature;
import com.sample.dao.feature.FeatureDAO;
import com.sample.service.contants.BoNames;
import com.sample.service.welcome.WelcomeService;
import com.sample.shared.Constants;
import com.sample.shared.test.TestConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleBusinessServiceApplication.class)
@DataJpaTest
public class WelcomeServiceIT {

	@Autowired
	@Qualifier(Constants.Services.WelcomeService.SERVICE)
	private WelcomeService mOBWelcomeService;

	@Autowired
	private CustomerDao mOBCustomerRepository;

	@Autowired
	private FeatureDAO mOBFeatureDAO;

	@Before
	public void loadData() {
		demoFeature();
		demoCustomer();
	}

	@Test
	@Repeat(value = 2)
	public void welcomeId() {
		Integer lINCustomerId = TestConstants.CUSTOMER_ID;

		// Run test for all welcome features.
		List<String> lOBWelcomeFeatures = getWelcomeFeatures();
		for (Iterator<String> iterator = lOBWelcomeFeatures.iterator(); iterator.hasNext();) {
			String lSTWelcomeFeature = (String) iterator.next();

			Feature lOBFeature = mOBFeatureDAO.findOne(Constants.Services.WelcomeService.SERVICE);
			lOBFeature.setFeatureName(lSTWelcomeFeature);
			mOBFeatureDAO.save(lOBFeature);

			runWelcomeId(lINCustomerId);
		}
	}

	private void demoFeature() {
		Feature lOBFeature = new Feature(Constants.Services.WelcomeService.SERVICE, BoNames.BO_WELCOME_DEFAULT,
				Boolean.TRUE);

		mOBFeatureDAO.save(lOBFeature);
	}

	private void demoCustomer() {
		// save a couple of customers
		mOBCustomerRepository.save(new Customer("Gökçe", "Tuna"));
		mOBCustomerRepository.save(new Customer("Fatih", "Çelikel"));
		mOBCustomerRepository.save(new Customer("Muharrem", "Gün"));
		mOBCustomerRepository.save(new Customer("Serhat", "Dirik"));
	}

	private List<String> getWelcomeFeatures() {
		List<String> lOBWelcomeFeatures = new ArrayList<>();
		lOBWelcomeFeatures.add(BoNames.BO_WELCOME_DEFAULT);
		lOBWelcomeFeatures.add(BoNames.BO_WELCOME_TEST01);
		lOBWelcomeFeatures.add(BoNames.BO_WELCOME_TEST02);
		return lOBWelcomeFeatures;
	}

	private void runWelcomeId(Integer pINCustomerId) {
		// Call welcome service
		Response lOBResponse = mOBWelcomeService.welcomeId(pINCustomerId);

		assertThat(lOBResponse.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertNotNull(lOBResponse.getEntity());
	}
}
