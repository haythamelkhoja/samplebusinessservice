package com.sample.service.test.welcome;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sample.bo.welcome.IWelcomeBO;
import com.sample.service.BusinessObjectFactory;
import com.sample.service.contants.BoNames;
import com.sample.service.welcome.WelcomeService;
import com.sample.shared.Constants;
import com.sample.shared.exception.SharedException;
import com.sample.shared.welcome.WelcomePojo;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeServiceTest {

	@InjectMocks
	private WelcomeService mOBWelcomeService = new WelcomeService();

	@Mock
	private BusinessObjectFactory mOBBusinessObjectFactory;

	@Mock
	@Qualifier(BoNames.BO_WELCOME_DEFAULT)
	private IWelcomeBO mOBWelcomeBO;

	@Test
	public void welcome() throws SharedException {
		// given
		String pSTName = "lorem";
		WelcomePojo wp = new WelcomePojo(pSTName, new Date());
		given(mOBWelcomeBO.welcome(pSTName)).willReturn(wp);
		given(mOBBusinessObjectFactory.getBusinessObject(Constants.Services.WelcomeService.SERVICE))
				.willReturn(mOBWelcomeBO);

		// when
		Response actual = mOBWelcomeService.welcome(pSTName);

		// then
		assertNotNull(actual);
		assertThat(actual.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertNotNull(actual.getEntity());
		assertEquals(actual.getEntity(), wp);
	}

	@Test
	public void welcomeId() throws SharedException {
		// given
		String pSTName = "lorem";
		WelcomePojo wp = new WelcomePojo(pSTName, new Date());

		given(mOBWelcomeBO.welcomeId(1)).willReturn(wp);
		given(mOBBusinessObjectFactory.getBusinessObject(Constants.Services.WelcomeService.SERVICE))
				.willReturn(mOBWelcomeBO);

		// when
		Response actual = mOBWelcomeService.welcomeId(1);

		// then
		assertNotNull(actual);
		assertThat(actual.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertNotNull(actual.getEntity());
		assertEquals(actual.getEntity(), wp);
	}

	// TODO test exceptions
	// TODO test welcome()

}
