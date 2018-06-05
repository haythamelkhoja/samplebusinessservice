package com.sample.bo.welcome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.dao.customer.Customer;
import com.sample.dao.customer.CustomerDao;
import com.sample.shared.test.TestConstants;
import com.sample.shared.welcome.WelcomePojo;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeBOsTest {

	@InjectMocks
	private WelcomeBO mOBWelcomeBO;

	@InjectMocks
	private WelcomeBO2 mOBWelcomeBO2;

	@InjectMocks
	private WelcomeBO3 mOBWelcomeBO3;

	@Mock
	private CustomerDao mOBCustomerRepository;

	@Mock
	private Greeting greeting;

	@Test
	public void testWelcomeId() {
		String lSTExpectedCustomerName1 = TestConstants.CUSTOMER_NAME + " " + TestConstants.CUSTOMER_LASTNAME;
		testForId(mOBWelcomeBO, lSTExpectedCustomerName1);

		String lSTExpectedCustomerName2 = TestConstants.CUSTOMER_NAME + " - " + TestConstants.CUSTOMER_LASTNAME;
		testForId(mOBWelcomeBO2, lSTExpectedCustomerName2);

		String lSTExpectedCustomerName3 = TestConstants.CUSTOMER_NAME + " -- " + TestConstants.CUSTOMER_LASTNAME;
		testForId(mOBWelcomeBO3, lSTExpectedCustomerName3);
	}

	@Test
	public void testWelcome() {
		testForWelcome(mOBWelcomeBO, TestConstants.CUSTOMER_NAME);

		testForWelcome(mOBWelcomeBO2, TestConstants.CUSTOMER_NAME);

		testForWelcome(mOBWelcomeBO3, TestConstants.CUSTOMER_NAME);
	}

	private void testForId(IWelcomeBO pOBWelcomeBO, String pSTExpectedCustomerName) {
		// Given
		Customer exptected = new Customer(TestConstants.CUSTOMER_NAME, TestConstants.CUSTOMER_LASTNAME);
		given(mOBCustomerRepository.findOne(TestConstants.CUSTOMER_ID)).willReturn(exptected);
		given(greeting.getMessage(pSTExpectedCustomerName)).willReturn(pSTExpectedCustomerName);

		// when
		WelcomePojo actual = pOBWelcomeBO.welcomeId(TestConstants.CUSTOMER_ID);

		// then
		assertNotNull(actual);
		assertEquals(pSTExpectedCustomerName, actual.getMessage());

	}

	private void testForWelcome(IWelcomeBO pOBWelcomeBO, String pSTExpectedCustomerName) {

		// Given
		given(greeting.getMessage(pSTExpectedCustomerName)).willReturn("lorem" + pSTExpectedCustomerName + "ipsum");

		// when
		WelcomePojo actual = pOBWelcomeBO.welcome(pSTExpectedCustomerName);

		// then
		assertNotNull(actual);
		assertEquals("lorem" + pSTExpectedCustomerName + "ipsum", actual.getMessage());
	}
}
