package com.sample.service.welcome;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.sample.bo.welcome.IWelcomeBO;
import com.sample.service.BaseService;
import com.sample.shared.Constants;
import com.sample.shared.exception.SharedException;
import com.sample.shared.welcome.WelcomePojo;

/**
 * 
 * @author N44947
 *
 *         Business service for welcome operations.
 */
@Service(Constants.Services.WelcomeService.SERVICE)
@Path(Constants.Services.WelcomeService.PATH)
public class WelcomeService extends BaseService {

	private IWelcomeBO getWelcomeBO() throws SharedException {
		return (IWelcomeBO) getBoFactory().getBusinessObject(Constants.Services.WelcomeService.SERVICE);
	}

	@GET
	@Path(Constants.Services.WelcomeService.Welcome.PATH)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response welcome(@PathParam(Constants.Services.WelcomeService.Welcome.PARAM_NAME) String pSTName) {
		try {
			WelcomePojo lOBWelcomePojo = getWelcomeBO().welcome(pSTName);
			return Response.status(Response.Status.OK.getStatusCode()).entity(lOBWelcomePojo).build();
		} catch (SharedException e) {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(Constants.Services.WelcomeService.WelcomeId.PATH)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response welcomeId(@PathParam(Constants.Services.WelcomeService.WelcomeId.PARAM_ID) Integer pNUId) {
		try {			
			WelcomePojo lOBWelcomePojo = getWelcomeBO().welcomeId(pNUId);
			return Response.status(Response.Status.OK.getStatusCode()).entity(lOBWelcomePojo).build();
			
		} catch (SharedException e) {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode()).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path(Constants.Services.WelcomeService.Welcome.NAME)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response welcome() {
		return welcome(null);
	}
}
